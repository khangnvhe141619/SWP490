package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.entity.Image;
import com.workshop.carauctionsystem.repository.CarRepository;
import com.workshop.carauctionsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository repository;

    @Override
    public List<Car> getAllCar() {
        return (List) repository.findAll();
    }


}
