package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
