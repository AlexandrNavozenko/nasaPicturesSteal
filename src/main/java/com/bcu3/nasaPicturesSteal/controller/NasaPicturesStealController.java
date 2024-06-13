package com.bcu3.nasaPicturesSteal.controller;


import com.bcu3.nasaPicturesSteal.dto.PictureDto;
import com.bcu3.nasaPicturesSteal.dto.StealNasaPicturesRequestDto;
import com.bcu3.nasaPicturesSteal.servise.NasaPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nasa/pictures")
@RequiredArgsConstructor
public class NasaPicturesStealController {

    private final NasaPictureService nasaPictureService;

    @PostMapping("/steal")
    @ResponseStatus(HttpStatus.CREATED)
    public void stealPictures(@RequestBody StealNasaPicturesRequestDto stealNasaPicturesRequestDto) {
        nasaPictureService.stealPictures(stealNasaPicturesRequestDto);
    }

    @GetMapping
    public Page<PictureDto> getStolenPictures(Pageable pageable) {
        return nasaPictureService.getStolenPictures(pageable);
    }
}
