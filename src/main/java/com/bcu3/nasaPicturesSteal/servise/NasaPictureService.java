package com.bcu3.nasaPicturesSteal.servise;

import com.bcu3.nasaPicturesSteal.dto.NasaCameraDto;
import com.bcu3.nasaPicturesSteal.dto.NasaPictureDto;
import com.bcu3.nasaPicturesSteal.dto.NasaResponseDto;
import com.bcu3.nasaPicturesSteal.dto.PictureDto;
import com.bcu3.nasaPicturesSteal.dto.StealNasaPicturesRequestDto;
import com.bcu3.nasaPicturesSteal.entity.Camera;
import com.bcu3.nasaPicturesSteal.entity.Picture;
import com.bcu3.nasaPicturesSteal.exception.PictureStealingException;
import com.bcu3.nasaPicturesSteal.repo.CameraRepository;
import com.bcu3.nasaPicturesSteal.repo.PictureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NasaPictureService {

    private static final RestTemplate restTemplate = new RestTemplate();

    private final CameraRepository cameraRepository;

    private final PictureRepository pictureRepository;

    private final Map<Long, Camera> camerasCache = new HashMap<>();

    @Value("${nasa.picture.url}")
    private String nasaUrl;

    @Value("${nasa.api.key}")
    private String nasaApiKey;

    @Transactional
    public void stealPictures(StealNasaPicturesRequestDto nasaPictureRequest) {
        try {
            log.info(String.format("Start processing NASA pictures for sol: %s", nasaPictureRequest.sol()));

            String url = buildUrl(nasaPictureRequest);
            NasaResponseDto nasaResponseDto = restTemplate.getForObject(url, NasaResponseDto.class);

            nasaResponseDto.getNasaPictureDtos()
                    .stream()
                    .forEach(this::savePicture);
            camerasCache.clear();

            log.info(String.format("NASA pictures for sol '%s' has been successfully stolen!", nasaPictureRequest.sol()));
        } catch (Exception e) {
            log.error(String.format("Error saving pictures from NASA for sol: %s", nasaPictureRequest.sol()), e);
            throw new PictureStealingException(String.format("Error saving pictures from NASA for sol: %s", nasaPictureRequest.sol()));
        }
    }

    private String buildUrl(StealNasaPicturesRequestDto stealNasaPicturesRequestInfoDto) {
        return UriComponentsBuilder.fromHttpUrl(nasaUrl)
                .queryParam("sol", stealNasaPicturesRequestInfoDto.sol())
                .queryParam("api_key", nasaApiKey)
                .build()
                .toString();
    }

    public void savePicture(NasaPictureDto nasaPictureInfoDto) {
        NasaCameraDto nasaCameraInfoDto = nasaPictureInfoDto.getCamera();
        Long nasaCameraId = nasaCameraInfoDto.getId();
        Camera camera = Optional.ofNullable(camerasCache.get(nasaCameraId))
                .or(() -> cameraRepository.findCameraByNasaId(nasaCameraId))
                .orElseGet(() -> Camera.build(nasaCameraInfoDto));
        camerasCache.putIfAbsent(nasaCameraId, camera);

        Picture picture = Picture.build(nasaPictureInfoDto);
        picture.setCamera(camera);

        saveCameraIfDoesNotExist(camera);

        pictureRepository.save(picture);
    }

    private void saveCameraIfDoesNotExist(Camera camera) {
        if (camera.getId() == null) {
            cameraRepository.save(camera);
        }
    }

    public Page<PictureDto> getStolenPictures(Pageable pageable) {
        return pictureRepository.getAllStolenPictures(pageable);
    }
}
