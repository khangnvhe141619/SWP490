package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BrandService {

    List<Brand> getAllBrand();
    public void saveBrand(Brand brand);
    public void delete(Long id) throws NotFoundException;
    public Brand findById(Long id) throws NotFoundException;
    public Page<Brand> findAllOrderById(Pageable pageable);

}
