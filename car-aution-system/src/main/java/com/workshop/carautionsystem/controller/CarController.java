package com.workshop.carautionsystem.controller;

import com.workshop.carautionsystem.Validates.Vadidate;
import com.workshop.carautionsystem.model.Car;
import com.workshop.carautionsystem.model.ResponseObject;
import com.workshop.carautionsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/v1/car")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping("/list")
    //this request is: http://localhost:8080/api/v1/car/listAllCar
    //get all car
    public  ResponseEntity<Object> showCarList() {
        List<Car> listCar = service.listAllCar();
        if(!CollectionUtils.isEmpty(listCar)){
            return ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject("ok","List Car",listCar)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","List Found")
            );
        }
    }

    //get car by id ( lấy cả detail car)
    @GetMapping("/{id}")
    ResponseEntity<?> findByIḍ(@PathVariable Integer id) {
        Optional<Car> foundCar = service.getCarById(id);
        if (foundCar.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    foundCar.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find car with id =  " + id)
            );
        }
    }

    //add new car
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertCar(@RequestBody Car newCar) {
        //check info exist car
        Vadidate validate = new Vadidate();
        if (validate.isExistCar(service.listAllCar(), newCar.getNameCar(), newCar.getModel(),
                newCar.getColor(), newCar.getCartypeID(), newCar.getCategoryID())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Already exist information of this car", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Car Successfully", service.addNewCar(newCar))
        );
    }

    //delete car
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteCar(@PathVariable Integer id){
        boolean exist = service.existCar(id);
        if(exist == true){
            service.deleteCar(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete Car Successfully", "")
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Delete Car Failed", "")
            );
        }
    }

    //update car
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateCar(@RequestBody Car updateCar, @PathVariable Integer id) {
        Optional<Car> foundCar = service.getCarById(id);
        if (foundCar.isPresent()) {
            Car car = foundCar.get();
            if (car.getNameCar() != null) {
                car.setNameCar(updateCar.getNameCar());
            }
            if (car.getPicture() != null) {
                car.setPicture(updateCar.getPicture());
            }
            if (car.getPrice() > 0) {
                car.setPrice(updateCar.getPrice());
            }
            if (car.getModel() != null) {
                car.setModel(updateCar.getModel());
            }
            if (car.getYearMake() > 0) {
                car.setYearMake(updateCar.getYearMake());
            }
            if (car.getColor() != null) {
                car.setColor(updateCar.getColor());
            }
            if (car.getCartypeID().getId()> 0) {
                car.setCartypeID(updateCar.getCartypeID());
            }
            if (car.getCategoryID().getId() > 0) {
                car.setCategoryID(updateCar.getCategoryID());
            }
            if (car.getSessionID().getId() > 0) {
                car.setSessionID(updateCar.getSessionID());
            }
            if (car.getSellerID().getId() > 0) {
                car.setSellerID(updateCar.getSellerID());
            }
            if (car.getDescription() != null) {
                car.setDescription(updateCar.getDescription());
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update car successfully", service.updateCar(car))
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Update car failed", "")
            );
        }
    }

    //search car by name
    @GetMapping("/search")
    ResponseEntity<Object> searchCarByName (@RequestParam("name") String name) {
        if (name != null) {
            List<Car> listSearch = service.listSearchCar(name);
            if (!CollectionUtils.isEmpty(listSearch)) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "List Search", listSearch)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "List Found")
                );
            }
        } else {
            List<Car> listCar = service.listAllCar();
            if (!CollectionUtils.isEmpty(listCar)) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "List Car", listCar)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "List Found")
                );
            }
        }
    }

    @GetMapping("/search/{id}")
    ResponseEntity<Object> searchCarByCategory (@PathVariable Integer id) {
        List<Car> listSearch = service.listFiterCarByCategory(id);
        if (!CollectionUtils.isEmpty(listSearch)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "List Search", listSearch)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "List Found")
            );
        }
    }
}
