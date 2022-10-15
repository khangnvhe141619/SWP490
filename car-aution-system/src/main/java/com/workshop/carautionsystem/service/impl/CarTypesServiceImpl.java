package com.workshop.carautionsystem.service.impl;


import com.workshop.carautionsystem.model.CarTypes;
import com.workshop.carautionsystem.responsitory.CarTypesResponsitory;
import com.workshop.carautionsystem.service.CarTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarTypesServiceImpl implements CarTypesService {
    @Autowired
    private CarTypesResponsitory repo;

    @Override
    public List<CarTypes> listAllCarTypes() {
        return (List<CarTypes>) repo.findAll() ;
    }

    @Override
    public int deleteCarTypes(int id) {
        return 0;
    }

    @Override
    public boolean addNewCarTypes(CarTypes carTypes) {
        return false;
    }

    @Override
    public boolean updateCarTypes(CarTypes carTypes) {
        return false;
    }

    @Override
    public List<CarTypes> listSearchCarTypes() {
        return null;
    }

    @Override
    public boolean getCarTypesById(int id) {
        return false;
    }
}
