package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.model.CarDTO;
import com.workshop.carauctionsystem.repository.CarRepository;
import com.workshop.carauctionsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        List<Car> lc=carRepository.fetchAllCar();
        lc.forEach(l -> System.out.println(l));
        return lc;
    }


}
