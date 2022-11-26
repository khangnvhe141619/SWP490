package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.CarSpecification;
import com.workshop.carauctionsystem.repository.CarSpecificationRepository;
import com.workshop.carauctionsystem.service.CarSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CarSpecificationServiceImpl implements CarSpecificationService {
    @Autowired
    CarSpecificationRepository carSpecificationRepository;

    @Override
    public CarSpecification getAllByCarId(Long id) {
        return carSpecificationRepository.findAllByCarId(id);
    }
}
