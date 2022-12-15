package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.entity.ModelSpecification;
import com.workshop.carauctionsystem.exception.NotFoundException;
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
class ModelSpecificationServiceImplTest {
    @Autowired
    ModelSpecificationServiceImpl modelSpecificationService;

    @Test
    void whenSearch_thenListMSFound() {
        List<ModelSpecification> lst = modelSpecificationService.getAllModelSpecification();
        assertEquals(13, lst.size());
    }

    @Test
    void givenValidInformation_whenSaveModelSpecification_thenSucceed() {
        ModelSpecification ms = new ModelSpecification(18l, "SAV", 4, 1);
        modelSpecificationService.saveModelSpecification(ms);
    }

    @Test
    void givenValidInformation_whenUpdateModelSpecification_thenSucceed() {
        modelSpecificationService.updateModelSpec("SUX", 5, 1l);
    }

    @Test
    void givenValidInformation_whenDeleteModelSpecification_thenSucceed() throws NotFoundException {
        modelSpecificationService.delete(1l);
    }

    @Test
    void givenValidId_whenSearch_thenModelSpecFound() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<ModelSpecification> pm = modelSpecificationService.findAllOrderById(pageable);
        assertEquals(5, pm.getNumberOfElements());
    }

    @Test
    void whenSearch_thenListFound() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<ModelSpecification> pm = modelSpecificationService.findAllOrderById(pageable);
        assertEquals(5, pm.getNumberOfElements());
    }

    @Test
    void givenValidName_whenSearch_thenListFound() {
        String name = "SUV";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<ModelSpecification> pm = modelSpecificationService.findAllOrderByName(pageable, name);
        assertEquals(2, pm.getNumberOfElements());
        Page<ModelSpecification> pm1 = modelSpecificationService.findAllOrderByName(pageable, null);
        assertEquals(5, pm1.getNumberOfElements());
    }
}