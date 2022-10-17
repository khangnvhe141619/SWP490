package com.workshop.carautionsystem.controller;

import com.workshop.carautionsystem.model.CarCategory;
import com.workshop.carautionsystem.model.ResponseObject;
import com.workshop.carautionsystem.repository.CarCategoryRepository;
import com.workshop.carautionsystem.service.CarCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/CarCategory")
public class CarCategoryController {
    @Autowired
    CarCategoryService service;

    @Autowired
    CarCategoryRepository repository;

    @GetMapping("")
    public List<CarCategory> getAllCarCategory(){
        return service.listAll();
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertCarBrand(@RequestBody CarCategory newNameBrand){
        Optional<CarCategory> carCategories = repository.findCarCategoryByNameBrand(newNameBrand.getNameBrand().trim());
        if(!carCategories.isPresent()){
            service.addCarCategory(newNameBrand);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Add successfully"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Failed", "Name Brand already taken"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteCarBrand(@PathVariable int id){
        boolean check = repository.existsById(id);
        if(check){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete successfully"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Failed", "Id not already taken"));
    }
}
