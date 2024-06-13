package com.bcu3.nasaPicturesSteal.entity;

import com.bcu3.nasaPicturesSteal.dto.NasaPictureDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.Temporal;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nasa_id", unique = true, nullable = false)
    private Long nasaId;

    @Column(name = "img_src")
    private String imgSrc;

    @ManyToOne(optional = false)
    @JoinColumn(name = "camera_id")
    private Camera camera;

    @UpdateTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    public static Picture build(final NasaPictureDto nasaPictureInfoDto) {
        return Picture.builder()
                .nasaId(nasaPictureInfoDto.getId())
                .imgSrc(nasaPictureInfoDto.getImgSrc())
                .createdAt(nasaPictureInfoDto.getCreatedAt())
                .build();
    }
}
