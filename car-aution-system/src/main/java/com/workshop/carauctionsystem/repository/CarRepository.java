package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
