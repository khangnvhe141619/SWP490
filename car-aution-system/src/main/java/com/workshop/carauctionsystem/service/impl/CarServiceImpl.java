package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Car;

import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.repository.CarRepository;
import com.workshop.carauctionsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;


    @Override
    public Page<Car> findAllByCarName(Pageable pageable, String name) {
        if (name != null) {
            return carRepository.findAllByCarName(pageable, name);
        } else {
            return carRepository.findAllActiveCar(pageable);
        }
    }

    @Override
    public List<Car> findAllDTO() {
        List<Car> lc = carRepository.fetchAllCar();
        lc.forEach(l -> System.out.println(l));
        return lc;
    }

    @Override
    public List<Car> getAllModel() {
        return null;
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void updateCar(Long createdBy, String description, Long upBoundPrice, Long downBoundPrice, Timestamp updatedAt, Long id) {

    }

    @Override
    public void delete(Long id) throws NotFoundException {

    }

    @Override
    public ModelCar findById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public Car getAllCarById(Long id) {
        return carRepository.getAllCarById(id);
    }

}
