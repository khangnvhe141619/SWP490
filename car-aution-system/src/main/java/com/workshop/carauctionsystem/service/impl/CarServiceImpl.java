package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.entity.Car;
import com.workshop.carautionsystem.entity.Image;
import com.workshop.carautionsystem.repository.CarRepository;
import com.workshop.carautionsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository repository;

    @Override
    public List<Car> getAllCar() {
        return repository.findAll();
    }


}
