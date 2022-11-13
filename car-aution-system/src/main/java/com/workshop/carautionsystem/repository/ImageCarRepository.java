package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageCarRepository extends JpaRepository<Image, Integer> {
}
