package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.model.Car;
import com.workshop.carautionsystem.responsitory.CarResponsitory;
import com.workshop.carautionsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarResponsitory repo;

    @Override
    public List<Car> listAllCar() {
        return (List<Car>) repo.findAll();
    }

    @Override
    public int deleteCar(int id) {
        return 0;
    }

    @Override
    public boolean addNewCar(Car car) {
        return false;
    }

    @Override
    public boolean updateCar(Car car) {
        return false;
    }

    @Override
    public List<Car> listSearchCar() {
        return null;
    }

    @Override
    public boolean getCarById(int id) {
        return false;
    }
}
