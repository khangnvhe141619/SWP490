package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.repository.BrandRepository;
import com.workshop.carauctionsystem.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrand() {
        return (List) brandRepository.findAll();
    }

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Long count = brandRepository.countById(id);
        if(count == null || count == 0){
            throw new NotFoundException("Could not find any with ID" + id);
        }
        brandRepository.deleteById(id);

    }

    @Override
    public Brand findById(Long id) {
        Optional<Brand> optional = brandRepository.findById(id);
        Brand brand = null;
        if(optional.isPresent()){
            brand = optional.get();
        }
        return brand;
    }

    @Override
    public Page<Brand> findAllOrderById(Pageable pageable) {

        return brandRepository.findAllOrderById(pageable);
    }
}
