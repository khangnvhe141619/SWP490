package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.CarSpecification;

public interface CarSpecificationService {
    public void saveCarSpecification(CarSpecification carSpecification);
    public void update(String manufacturing, String km_driven, String gear, String fuel, String fuelConsumption, String outerColor, String innerColor,String overallDimensions,String drive,String yearOfMake,Long id);

    public CarSpecification getAllByCarId(Long id);
}
