package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.model.CarTypes;

import java.util.List;

public interface CarTypesService {

    //list all types car
    public List<CarTypes> listAllCarTypes();

    //delete car types
    public int deleteCarTypes(int id);

    //add new car types
    public boolean addNewCarTypes (CarTypes carTypes);

    //update car
    public boolean updateCarTypes(CarTypes carTypes);

    //search car types ( can not need)
    public List<CarTypes> listSearchCarTypes();

    //get car by id
    public boolean getCarTypesById(int id);
}
