package com.workshop.carauctionsystem.model;

import com.workshop.carauctionsystem.entity.Car;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class CarSpecificationDTO {
    private Long id;

    private Car carId;

    private String manufacturing;

    private String status;

    private String km_driven;

    private String gear;

    private String fuel;

    private String fuelConsumption;

    private String outerColor;

    private String innerColor;

    private String overallDimension;

    private String drive;

    private String yearOfMake;
}
