package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.repository.BrandRepository;
import com.workshop.carauctionsystem.service.BrandService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
class BrandServiceImplTest {
    @Autowired
    private BrandRepository repo;
    @Autowired
    private BrandServiceImpl brandServiceImpl;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        brandServiceImpl = new BrandServiceImpl(repo);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
//1
    @Test
    void getAllBrand() {
        List<Brand> lst = brandServiceImpl.getAllBrand();
        assertEquals(14, lst.size());
    }
//2
    @Test
    void givenValidBrand_whenSaveBrand_thenSucceed() {
        Brand b1 = new Brand("VINFAST", "/hoang/vinfast.jpg", Long.parseLong("0"));
        brandServiceImpl.saveBrand(b1);
    }
//3
    @Test
    void givenValidBrandNotStatus_whenSaveBrand_thenSucceed() {
        Brand b1 = new Brand("VINFAST", "/hoang/vinfast.jpg", Long.parseLong("1"));
        brandServiceImpl.saveBrand(b1);
    }
//4
    @Test
    void givenValidBrand_whenUpdateBrand_thenSucceed() {
        Brand b1 = new Brand("FANVIST", "/hoang/vinfast.jpg", Long.parseLong("1"));
        brandServiceImpl.updateBrand(b1.getBrandName(), b1.getImgPath(), Long.parseLong("6"));
    }
//5
    @Test
    void whenValidId_thenDelete() throws NotFoundException {
        brandServiceImpl.delete(Long.parseLong("3"));
    }
//6
    @Test
    void whenValidId1_thenDelete() throws NotFoundException {
        brandServiceImpl.delete(Long.parseLong("100"));
    }
//7
    @Test
    void whenValidId_thenBrandFound() throws NotFoundException {
        Brand b = new Brand(Long.parseLong("1"), "VINFAST", "/hoang/vinfast.jpg", 1l);
        Brand newB = brandServiceImpl.findById(Long.parseLong("1"));
        assertEquals(b.getBrandName(), newB.getBrandName());
        assertEquals(b.getImgPath(), newB.getImgPath());
    }
//8
    @Test
    void whenValidId1_thenBrandFound() throws NotFoundException {
        Brand b = new Brand(Long.parseLong("1"), "TOYOTA", "/hoang/toyota.png", 1l);
        Brand newB = brandServiceImpl.findById(Long.parseLong("3"));
        assertEquals(b.getBrandName(), newB.getBrandName());
        assertEquals(b.getImgPath(), newB.getImgPath());
    }
//9
    @Test
    void findAllOrderById() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<Brand> pb = brandServiceImpl.findAllOrderById(pageable);
        assertEquals(4, pb.getTotalPages());
        assertEquals(20, pb.getTotalElements());
        assertEquals(5, pb.getNumberOfElements());
    }
//10
    @Test
    void whenNameNotEmpty_thenBrandsFound() {
        String name = "MAZDA";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<Brand> pb = brandServiceImpl.findAllOrderByName(pageable, name);
        assertEquals(1, pb.getNumberOfElements());
        assertEquals(1, pb.getTotalElements());
    }
//11
    @Test
    void whenNameNotEmpty1_thenBrandsFound() {
        String name = "VINFAST";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<Brand> pb = brandServiceImpl.findAllOrderByName(pageable, name);
        assertEquals(1, pb.getNumberOfElements());
        assertEquals(1, pb.getTotalElements());
    }
}