package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.CarSpecification;

public interface CarSpecificationService {
    public CarSpecification getAllByCarId(Long id);
}