package com.bcu3.nasaPicturesSteal.repo;

import com.bcu3.nasaPicturesSteal.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CameraRepository extends JpaRepository<Camera, Long> {

    Optional<Camera> findCameraByNasaId(Long nasaId);
}
