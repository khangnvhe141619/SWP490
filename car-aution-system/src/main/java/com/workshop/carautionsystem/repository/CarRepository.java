package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.model.Car;
import com.workshop.carautionsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {

   @Query("select c from Car c where c.nameCar like %?1%")
    public List<Car> searchCar (String query);

   @Query("select c from Car c where c.categoryID.id = ?1 ")
    public List<Car> findCarByCategoryID (Integer id);
}
