package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.model.CarTypes;
import org.springframework.data.repository.CrudRepository;

public interface CarTypesRepository extends CrudRepository<CarTypes,Integer> {
}
