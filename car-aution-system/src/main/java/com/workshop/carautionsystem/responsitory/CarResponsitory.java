package com.workshop.carautionsystem.responsitory;

import com.workshop.carautionsystem.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarResponsitory extends CrudRepository<Car,Integer> {
}
