package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.CarSpecification;
import com.workshop.carauctionsystem.repository.CarSpecificationRepository;
import com.workshop.carauctionsystem.service.CarSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarSpecificationServiceImpl implements CarSpecificationService {
    @Autowired
    private CarSpecificationRepository carSpecRepo;

    @Override
    public void saveCarSpecification(CarSpecification carSpecification) {
        carSpecRepo.save(carSpecification);
    }
}
