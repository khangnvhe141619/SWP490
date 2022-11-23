package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM image WHERE carId = :carId ")
    public List<Image> findAllByCarId(Long carId);
}
