package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.CarSpecification;
import org.springframework.data.repository.CrudRepository;

public interface CarSpecificationRepository extends CrudRepository<CarSpecification,Long> {
}
