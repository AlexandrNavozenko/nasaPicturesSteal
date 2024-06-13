package com.bcu3.nasaPicturesSteal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class NasaCameraDto {

    private Long id;

    private String name;

    @JsonProperty("full_name")
    private String fullName;
}
