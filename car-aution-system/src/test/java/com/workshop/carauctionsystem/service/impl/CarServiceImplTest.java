package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@Rollback(value = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {
        "com.workshop.carauctionsystem.repository",
        "com.workshop.carauctionsystem.service"
})
class CarServiceImplTest {
    @Autowired
    CarRepository carRepository;
    @Autowired
    CarServiceImpl carServiceImpl;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void whenNameNotEmpty_thenCarsFound() {
        String name = "f9";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<Car> pc = carServiceImpl.findAllByCarName(pageable, name);
        assertEquals(1, pc.getNumberOfElements());
    }

    @Test
    void findAllDTO() {
        List<Car> lc = carServiceImpl.findAllDTO();
        assertEquals(8, lc.size());
    }

    @Test
    @Disabled
    void getAllModel() {
    }

    @Test
    void givenValidCar_whenSaveCar_thenSucceed() {
        ModelCar modelCar = new ModelCar();
        modelCar.setId(Long.parseLong("6"));
        User userCreate = new User();
        userCreate.setId(1);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Car c = new Car("Vinfast f1", "aaa",
                Long.parseLong("10"), Long.parseLong("20"),
                modelCar, 1, userCreate, timestamp, timestamp);
        carServiceImpl.saveCar(c);
    }

    @Test
    void givenValidId_whenUpdateCar_thenSucceed() {
        ModelCar modelCar = new ModelCar();
        modelCar.setId(Long.parseLong("6"));
        User userCreate = new User();
        userCreate.setId(1);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Car c = new Car("Vinfast f1", "aaa",
                Long.parseLong("10"), Long.parseLong("20"),
                modelCar, 1, userCreate, timestamp, timestamp);
        carServiceImpl.updateCar(Long.parseLong("1"), "aaa", Long.parseLong("22"),
                Long.parseLong("22"), timestamp, "Vinfast f9", Long.parseLong("1"));
    }

    @Test
    void givenValidId_whenDeleteCar_thenSucceed() throws NotFoundException {
        carServiceImpl.delete(Long.parseLong("2"));
    }

    @Test
    @Disabled
    void findById() {

    }

    @Test
    void whenValidId_thenCarFound() {
        ModelCar modelCar = new ModelCar();
        modelCar.setId(Long.parseLong("6"));
        User userCreate = new User();
        userCreate.setId(1);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Car c = new Car("Vinfast f9", "aaa",
                Long.parseLong("22"), Long.parseLong("22"),
                modelCar, 1, userCreate, timestamp, timestamp);
        Car c1 = carServiceImpl.getAllCarById(Long.parseLong("1"));
        assertEquals(c.getCarName(), c1.getCarName());
        assertEquals(c.getDownBoundPrice(), c1.getDownBoundPrice());
        assertEquals(c.getUpBoundPrice(), c1.getUpBoundPrice());
    }
}