package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Car;

import com.workshop.carauctionsystem.entity.Image;
import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.exception.NotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface CarService {
    public Page<Car> findAllByCarName(Pageable pageable, String name);

    public List<Car> findAllDTO();

    public void saveCar(Car car);

    public void updateCar(Long createdBy, String description, Long upBoundPrice, Long downBoundPrice, Timestamp updatedAt, String carName, Long id);

    public void delete(Long id) throws NotFoundException;

    public Car getAllCarById(Long id);
}
