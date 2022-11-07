package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.entity.Brand;
import com.workshop.carautionsystem.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface BrandService {
    List<Brand> getAllBrand();
    public void saveBrand(Brand brand);
    public void delete(Long id) throws NotFoundException;
    public Brand findById(Long id) throws NotFoundException;
    public Page<Brand> findAll(Pageable pageable);

    public Page<Brand> findAllById(Pageable pageable);

}
