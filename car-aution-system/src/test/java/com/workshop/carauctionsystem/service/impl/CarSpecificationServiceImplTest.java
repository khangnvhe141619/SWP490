package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.entity.CarSpecification;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@Rollback(value = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {
        "com.workshop.carauctionsystem.repository",
        "com.workshop.carauctionsystem.service"
})
class CarSpecificationServiceImplTest {
    @Autowired
    CarSpecificationServiceImpl carSpecificationService;

    @Test
    void givenValidInformation_whenSaveCarSpecification_thenSucceed() {
        Car c = new Car();
        c.setId(Long.parseLong("6"));
        CarSpecification cs = new CarSpecification(c, "Vinfast", "Old",
                "150", "9G TRONIC", "2.0L", "11,11/100km",
                "black", "Grey", "4.709 x 1.827 x 1.435 mm",
                "AWD - 4 wheelers full time", "2019"
        );
        carSpecificationService.saveCarSpecification(cs);
    }

    @Test
    void givenValidInformation_whenUpdateCarSpecification_thenSucceed() {
        carSpecificationService.update("VinFast",
                "15000", "9G TRONIC", "2.0L", "11,11/100km",
                "Grey", "Brown", "4.709 x 1.827 x 1.435 mm",
                "AWD - 4 wheelers full time", "2019", Long.parseLong("9"));
    }

    @Test
    void whenValidId_thenCarSpecificationFound() {
        Car c = new Car();
        c.setId(Long.parseLong("1"));
        CarSpecification cs = new CarSpecification(c, "VinFast ", "Old",
                "15000", "9G TRONIC", "2.0L", "11,11/100km",
                "Grey", "Brown", "4.709 x 1.827 x 1.435 mm",
                "AWD - 4 wheelers full time", "2019"
        );
        CarSpecification cs1 = carSpecificationService.getAllByCarId(Long.parseLong("1"));
        assertEquals(cs.getManufacturing(), cs1.getManufacturing());
        assertEquals(cs.getStatus(), cs1.getStatus());
        assertEquals(cs.getInnerColor(), cs1.getInnerColor());
        assertEquals(cs.getOuterColor(), cs1.getOuterColor());
        assertEquals(cs.getKm_driven(), cs1.getKm_driven());
    }
}