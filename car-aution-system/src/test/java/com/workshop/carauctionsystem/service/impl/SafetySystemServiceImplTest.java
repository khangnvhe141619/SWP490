package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.entity.SafetySystem;
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
class SafetySystemServiceImplTest {
    @Autowired
    SafetySystemServiceImpl safetySystemService;

    @Test
    void givenValidInformation_whenSaveSafetySystem_thenSucceed() {
        Car c = new Car();
        c.setId(1l);
        SafetySystem ss = new SafetySystem(17l, c, "4", "yes", "Yes", "yes", "No have car");
        safetySystemService.saveSafetySystem(ss);
    }

    @Test
    void whenSearch_thenListFound() {
        List<SafetySystem> lst = safetySystemService.getAllSafetySystem(1l);
        assertEquals(1, lst.size());
    }

    @Test
    void givenValidId_whenUpdate_thenSucceed() {
        safetySystemService.update("1", "yes", "Yes", "yes", "There is a car", 1l);
    }
}