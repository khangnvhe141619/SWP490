package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageCarRepository extends JpaRepository<Image, Integer> {
}
