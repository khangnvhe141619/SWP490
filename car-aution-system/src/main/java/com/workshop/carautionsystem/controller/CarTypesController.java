package com.workshop.carautionsystem.controller;

import com.workshop.carautionsystem.model.Car;
import com.workshop.carautionsystem.model.CarTypes;
import com.workshop.carautionsystem.service.CarTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/carTypes")
public class CarTypesController {

    @Autowired
    private CarTypesService service;

    @GetMapping("/listAllCarTypes")
    //this request is: http://localhost:8080/api/v1/car/listAllCar
    //get all car
    public List<CarTypes> showCarTypesList(){
        return service.listAllCarTypes();
    }

    //get car types by id
    //add new car types
    //delete car types
    //@GetMapping("/delete")
    //update car
    //search car
}
