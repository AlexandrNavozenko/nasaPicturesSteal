package com.bcu3.nasaPicturesSteal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NasaResponseDto {

    @JsonProperty("photos")
    private List<NasaPictureDto> nasaPictureDtos;
}