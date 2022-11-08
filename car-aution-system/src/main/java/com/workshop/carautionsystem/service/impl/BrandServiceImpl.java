package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.entity.Brand;
import com.workshop.carautionsystem.exception.NotFoundException;
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
    public void delete(Long id) throws NotFoundException{
        Long count = brandRepository.countById(id);
        if(count == null || count == 0){
            throw new NotFoundException("Could not find any with ID" + id);
        }
        brandRepository.deleteById(id);
    }

    @Override
    public Brand findById(Long id) throws NotFoundException{
        Optional<Brand> optional = brandRepository.findById(id);
        Brand brand = null;
        if(optional.isPresent()){
            brand = optional.get();
        }else {
            throw new NotFoundException("Could not find any with ID" + id);
        }
        return brand;
    }

    @Override
    public Page<Brand> findAll(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    @Override
    public Page<Brand> findAllById(Pageable pageable) {
        return brandRepository.findAllById(pageable);
    }

}