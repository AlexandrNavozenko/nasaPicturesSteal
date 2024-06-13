package com.bcu3.nasaPicturesSteal.repo;

import com.bcu3.nasaPicturesSteal.dto.PictureDto;
import com.bcu3.nasaPicturesSteal.entity.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query("select new com.bcu3.nasaPicturesSteal.dto.PictureDto(p.id, p.nasaId, p.imgSrc, p.createdAt, c.id, c.nasaId, c.name) from Picture p join p.camera c")
    Page<PictureDto> getAllStolenPictures(Pageable pageable);
}
