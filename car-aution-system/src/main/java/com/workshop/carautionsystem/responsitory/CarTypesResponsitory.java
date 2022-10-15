package com.workshop.carautionsystem.responsitory;

import com.workshop.carautionsystem.model.CarTypes;
import org.springframework.data.repository.CrudRepository;

public interface CarTypesResponsitory extends CrudRepository<CarTypes,Integer> {
}
