package com.bcu3.nasaPicturesSteal.entity;

import com.bcu3.nasaPicturesSteal.dto.NasaCameraDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cameras")
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nasa_id", unique = true, nullable = false)
    private Long nasaId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    public static Camera build(final NasaCameraDto nasaCameraDto) {
        return Camera.builder()
                .nasaId(nasaCameraDto.getId())
                .name(nasaCameraDto.getName())
                .createdAt(LocalDate.now())
                .build();
    }

}
