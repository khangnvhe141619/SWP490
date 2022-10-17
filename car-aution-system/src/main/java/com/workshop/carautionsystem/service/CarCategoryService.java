package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.model.CarCategory;

import java.util.List;

public interface CarCategoryService {
    public List<CarCategory> listAll();

    public CarCategory addCarCategory(CarCategory carCategory);
}
