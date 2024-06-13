package com.bcu3.nasaPicturesSteal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NasaPictureDto {

    private Long id;

    @JsonProperty("img_src")
    private String imgSrc;

    @JsonProperty("earth_date")
    private LocalDate createdAt;

    private NasaCameraDto camera;
}
