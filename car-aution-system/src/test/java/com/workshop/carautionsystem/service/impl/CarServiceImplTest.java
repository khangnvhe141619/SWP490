package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.Validates.Vadidate;
import com.workshop.carautionsystem.model.*;
import com.workshop.carautionsystem.repository.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
class CarServiceImplTest {


    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarServiceImpl carService;

    @Test
    public void testListAll() {
        Iterable<Car> cars = carRepository.findAll();
        Assertions.assertThat(cars).hasSizeGreaterThan(0);
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    @Test
    public void testGetCarById() {
        Integer carID = 1;
        Optional<Car> cars = carRepository.findById(carID);
        if (cars.isPresent() == true) {
            System.out.println(cars);
        } else {
            System.out.println("khong co xe nay");
        }
    }

    @Test
    public void testAddNew() {
        Car car = new Car();
        //  car.setId(2);
        car.setNameCar("Lamborghini Aventador");
        car.setPicture("aaa");
        car.setPrice(2000.0);
        car.setModel("2022");
        car.setYearMake(2022);
        car.setColor("Black");
        CarTypes carTypes = new CarTypes();
        carTypes.setId(2);
        Category category = new Category();
        category.setId(3);
        Session session = new Session();
        session.setId(2);
        User user = new User();
        car.setId(1);
        car.setDescription("aaa");
        //check info
        Vadidate vadidate = new Vadidate();
        if (vadidate.isExistCar(carRepository.findAll(), car.getNameCar(), car.getModel(),
                car.getColor(), car.getCartypeID(), car.getCategoryID())) {
            System.out.println("Already exist information of this car");

        } else {
            Car saveCar = carRepository.save(car);
            Assertions.assertThat(saveCar).isNotNull();
            Assertions.assertThat(saveCar.getId()).isGreaterThan(0);
        }
    }

    @Test
    public void testUpdate() {
        Integer carId = 4;
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setNameCar("Vinfart vf8");
            carRepository.save(car);
            System.out.println("Update successfully" + car);
        } else {
            System.out.println("Update failed");
        }
    }

    @Test
    public void testDelete() {
        Integer carId = 2;
        boolean exist = carService.existCar(carId);
        if (exist == true) {
            Optional<Car> optionalCar = carRepository.findById(carId);
            Car car = optionalCar.get();
            carRepository.delete(car);
            System.out.println("ok");
        } else {
            System.out.println("failed");
        }
    }

    @Test
    public void testSearchByName() {
        String txtSearch = "lam";
        List<Car> cars = carService.listSearchCar(txtSearch);
        if (cars.isEmpty() == false) {
            for (Car car : cars) {
                System.out.println(car);
            }
        } else {
            System.out.println("Khong tim thay xe");
        }
    }

    @Test
    public void testFiter(){
        Integer id = 3;
        List<Car> cars = carService.listFiterCarByCategory(id);
        if(cars.isEmpty() == false){
            for(Car car : cars){
                System.out.println(car);
            }
        }else{
            System.out.println("khong tim thay xe");
        }
    }
}