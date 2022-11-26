package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.SafetySystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SafetySystemRepository extends JpaRepository<SafetySystem, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM safetysystem WHERE carId = :carId")
    public List<SafetySystem> findAllByCarId(Long carId);
}
