package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface BrandService {
    List<Brand> getAllBrand();
    public void saveBrand(Brand brand);
    public void delete(long id);
    public Optional<Brand> findById(long id);
    public Page<Brand> findAll(Pageable pageable);

}
