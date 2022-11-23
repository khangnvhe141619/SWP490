package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

}
