package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.CarSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarSpecificationRepository extends JpaRepository<CarSpecification, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM carspecification WHERE carId = :id")
    public CarSpecification findAllByCarId(Long id);
}
