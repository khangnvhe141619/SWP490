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

    @Override
    public void update(String manufacturing, String km_driven, String gear, String fuel, String fuelConsumption, String outerColor, String innerColor, String overallDimensions, String drive, String yearOfMake, Long id) {
        carSpecRepo.update(manufacturing,km_driven,gear,fuel,fuelConsumption,outerColor,innerColor,overallDimensions,drive,yearOfMake,id);
    }

    @Override
    public CarSpecification getAllByCarId(Long id) {
        return carSpecRepo.findAllByCarId(id);
    }
}
