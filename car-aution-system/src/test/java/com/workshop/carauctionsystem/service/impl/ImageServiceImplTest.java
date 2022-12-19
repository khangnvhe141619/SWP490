package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.entity.Image;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

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
class ImageServiceImplTest {
    @Autowired
    ImageServiceImpl imageService;

    @Test
    void givenValidCarID_thenAllImageFound() {
        List<Image> lst = imageService.getAllImageByCarId(1l);
        assertEquals(3, lst.size());
    }

    @Test
    void givenValidCarID_whenSaveImage_thenSucceed() {
        Car car = new Car();
        car.setId(1l);
        Image img = new Image(9, car, "/img/Mer/m1.png");
        imageService.saveImageForCar(img);
    }
}