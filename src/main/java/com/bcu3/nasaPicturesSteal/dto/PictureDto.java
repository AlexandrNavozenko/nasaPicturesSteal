package com.bcu3.nasaPicturesSteal.dto;

import java.time.LocalDate;

public record PictureDto(Long id, Long nasaId, String imgSrc, LocalDate createdAt, Long cameraId, Long cameraNasaId, String cameraName) {
}
