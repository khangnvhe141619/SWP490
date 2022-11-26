package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    public Page<Car> findAllByCarName(Pageable pageable, String name);

    public Car getAllCarById(Long id);
}
