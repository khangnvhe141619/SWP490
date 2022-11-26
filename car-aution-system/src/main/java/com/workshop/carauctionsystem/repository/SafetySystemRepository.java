package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.SafetySystem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SafetySystemRepository extends CrudRepository<SafetySystem,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM safetysystem WHERE carId = :carId")
    public List<SafetySystem> findAllByCarId(Long carId);
}
