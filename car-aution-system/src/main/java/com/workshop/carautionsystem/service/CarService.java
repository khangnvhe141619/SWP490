package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.model.Car;
import com.workshop.carautionsystem.model.Category;

import java.util.List;
import java.util.Optional;

public interface CarService {

    //list all car ( by index page)
    public List<Car> listAllCar();

    //exist
    public boolean existCar(int id);

    //delete car
    public boolean deleteCar(int id);

    //add new car
    public Car addNewCar (Car car);

    //update car
    public Car updateCar(Car car);

    //search car by name and type (by index page)
    public List<Car> listSearchCar(String query);

    //fiter car by category
    public List<Car> listFiterCarByCategory(Integer id);

    //get car by id
    public Optional<Car> getCarById(Integer id);

}
