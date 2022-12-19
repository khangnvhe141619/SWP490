package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Brand;
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
class ModelServiceImplTest {
    @Autowired
    ModelServiceImpl modelService;

    @Test
    void whenSearch_thenListCarFound() {
        List<ModelCar> lst = modelService.getAllModelByStatus();
        assertEquals(60, lst.size());
    }

    @Test
    void givenValidModelCar_whenSaveModel_thenSucceed() {
        Brand b = new Brand();
        b.setId(1l);
        ModelSpecification ms = new ModelSpecification();
        ms.setId(1l);
        ModelCar mc = new ModelCar(61l, b, "Fadil", ms, 1);
        modelService.saveModel(mc);
    }

    @Test
    void givenValidInformation_whenUpdate_thenSucceed() {
        modelService.updateModel(2l, "LX", 1l, 1l);
    }

    @Test
    void givenValidId_whenDelete_thenSucceed() {
        try {
            modelService.delete(1l);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void givenValidId_whenSearch_thenModelFound() {
        modelService.findById(5l);
    }

    @Test
    void whenSearch_thenListFound_orderById() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<ModelCar> pm = modelService.findAllOrderById(pageable);
        assertEquals(60, pm.getTotalElements());
    }

    @Test
    void givenValidName_whenSearch_thenListFound() {
        String name = "LX";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<ModelCar> pm = modelService.findAllOrderByName(pageable, name);
        assertEquals(1, pm.getTotalElements());
        Page<ModelCar> pm1 = modelService.findAllOrderByName(pageable, null);
        assertEquals(5,pm1.getNumberOfElements());
    }

    @Test
    void givenValidBrandId_thenListModelFound() {
        List<ModelCar> lst = modelService.getModelByBrand(1l);
        assertEquals(3, lst.size());
    }
}