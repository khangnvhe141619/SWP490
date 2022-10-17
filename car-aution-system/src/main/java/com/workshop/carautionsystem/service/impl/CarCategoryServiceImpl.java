package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.model.CarCategory;
import com.workshop.carautionsystem.repository.CarCategoryRepository;
import com.workshop.carautionsystem.service.CarCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarCategoryServiceImpl implements CarCategoryService {
    @Autowired
    CarCategoryRepository repository;

    @Override
    public List<CarCategory> listAll() {
        return repository.findAll();
    }

    @Override
    public CarCategory addCarCategory(CarCategory carCategory) {
        return repository.save(carCategory);
    }
}
