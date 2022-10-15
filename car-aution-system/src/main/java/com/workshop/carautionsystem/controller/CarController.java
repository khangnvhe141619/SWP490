package com.workshop.carautionsystem.controller;

import com.workshop.carautionsystem.model.Car;
import com.workshop.carautionsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/car")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping("/listAllCar")
    //this request is: http://localhost:8080/api/v1/car/listAllCar
    //get all car
    public List<Car> showCarList(){
        return service.listAllCar();
    }

    //get car by id

    //add new car
    //delete car
    //@GetMapping("/delete")

    //update car
    //search car


}
