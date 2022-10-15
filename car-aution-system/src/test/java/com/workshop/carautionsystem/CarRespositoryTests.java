package com.workshop.carautionsystem;

import com.workshop.carautionsystem.model.Car;
import com.workshop.carautionsystem.responsitory.CarResponsitory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CarRespositoryTests {
    @Autowired private CarResponsitory repo;

       @Test
    public void testAddNew(){
        Car car = new Car();
      //  car.setId(2);
        car.setNameCar("Lamborghini Aventador");
        car.setPicture("aaa");
        car.setPrice(2000.0);
        car.setModel("2022");
        car.setYearOfMake(2022);
        car.setColor("Black");
        car.setCartypeID(2);
        car.setCategoryID(3);
        car.setSessionID(2);
        car.setSellerID(1);

        Car saveCar = repo.save(car);

        Assertions.assertThat(saveCar).isNotNull();
        Assertions.assertThat(saveCar.getId()).isGreaterThan(0);
    }


    /**
     * test load all car
     * da test chay ok
     */

    @Test
    public void testListAll(){
        Iterable<Car> cars =repo.findAll();
        Assertions.assertThat(cars).hasSizeGreaterThan(0);
        for(Car car : cars){
            System.out.println(car);
        }
    }

    @Test
    public void testGetCarById(){
        Integer carID = 2;
        Optional<Car> cars = repo.findById(carID);
        if (cars.isPresent() == true){
        System.out.println(cars);
        }else {
            System.out.println("khong co xe nay");
        }
    }

    @Test
    public void testUpdate(){
        Integer carId = 1;
        Optional<Car> optionalCar = repo.findById(carId);
        Car car = optionalCar.get();
        car.setNameCar("Vinfart vf8");
        repo.save(car);
        Car updateCar = repo.findById(carId).get();
        Assertions.assertThat(updateCar.getNameCar()).isEqualTo("Vinfart vf8");
    }

    @Test
    public void delete(){
        Integer carId = 2;
        Optional<Car> optionalCar = repo.findById(carId);
        Car car = optionalCar.get();
        repo.delete(car);
    }

}
