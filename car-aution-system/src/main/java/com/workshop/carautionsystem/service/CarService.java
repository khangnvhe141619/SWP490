package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.model.Car;

import java.util.List;

public interface CarService {

    //list all car ( by index page)
    public List<Car> listAllCar();

    //delete car
    public int deleteCar(int id);

    //add new car
    public boolean addNewCar (Car car);

    //update car
    public boolean updateCar(Car car);

    //search car by name and type (by index page)
    public List<Car> listSearchCar();

    //get car by id
    public boolean getCarById(int id);

}
