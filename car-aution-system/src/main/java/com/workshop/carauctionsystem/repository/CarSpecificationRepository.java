package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.CarSpecification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarSpecificationRepository extends CrudRepository<CarSpecification,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM carspecification WHERE carId = :id")
    public CarSpecification findAllByCarId(Long id);
}
