package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.model.Car;
import com.workshop.carautionsystem.model.Category;
import com.workshop.carautionsystem.repository.CarRepository;
import com.workshop.carautionsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository reponsitory;

    @Override
    public List<Car> listAllCar() {
        return (List<Car>) reponsitory.findAll();
    }

    public boolean existCar(int id){
        return reponsitory.existsById(id);
    }

    @Override
    public boolean deleteCar(int id) {
        try {
            reponsitory.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Car addNewCar(Car car) {
        return reponsitory.save(car);
    }

    @Override
    public Car updateCar(Car car) {
       return reponsitory.save(car);
    }

    @Override
    public List<Car> listSearchCar(String query) {
       return reponsitory.searchCar(query);
    }

    @Override
    public List<Car> listFiterCarByCategory(Integer id) {
        return reponsitory.findCarByCategoryID(id);
    }

    @Override
    public Optional<Car> getCarById(Integer id) {
        return reponsitory.findById(id);
    }

}
