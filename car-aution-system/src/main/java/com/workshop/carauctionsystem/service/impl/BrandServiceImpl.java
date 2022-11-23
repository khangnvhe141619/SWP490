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
        return (List) brandRepository.findAllByStatus();
    }

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void updateBrand(String name,String img,Long id) {
        brandRepository.update(name,img,id);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        brandRepository.delete(id);
    }

    @Override
    public Brand findById(Long id) {
        Optional<Brand> optional = brandRepository.findById(id);
        return optional.get();
    }

    @Override
    public Page<Brand> findAllOrderById(Pageable pageable) {
        return brandRepository.findAllOrderById(pageable);
    }

    @Override
    public Page<Brand> findAllOrderByName(Pageable pageable,String name) {
        if (name != null) {
            return brandRepository.findAllByBrandName(pageable, name);
        }else {
            return brandRepository.findAllOrderById(pageable);
        }
    }
}
