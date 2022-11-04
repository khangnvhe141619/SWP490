package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.model.Brand;
import com.workshop.carautionsystem.repository.BrandRepository;
import com.workshop.carautionsystem.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrand() {
        return (List<Brand>) brandRepository.findAll();
    }

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void delete(long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Optional<Brand> findById(long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Page<Brand> findAll(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }


}
