package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carspecification")
public class CarSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "carid")
    private Car carId;
    @Column(name = "manufacturing")
    private String manufacturing;
    @Column(name = "status")
    private String status;
    @Column(name = "km_driven")
    private String km_driven;
    @Column(name = "gear")
    private String gear;
    @Column(name = "fuel")
    private String fuel;
    @Column(name = "fuelconsumption")
    private String fuelConsumption;
    @Column(name = "outercolor")
    private String outerColor;
    @Column(name = "innercolor")
    private String innerColor;
    @Column(name = "overalldimensions")
    private String overallDimension;
    @Column(name = "drive")
    private String drive;
    @Column(name = "yearofmake")
    private String yearOfMake;

    public CarSpecification(Car carId, String manufacturing, String status, String km_driven, String gear, String fuel, String fuelConsumption, String outerColor, String innerColor, String overallDimension, String drive, String yearOfMake) {
        this.carId = carId;
        this.manufacturing = manufacturing;
        this.status = status;
        this.km_driven = km_driven;
        this.gear = gear;
        this.fuel = fuel;
        this.fuelConsumption = fuelConsumption;
        this.outerColor = outerColor;
        this.innerColor = innerColor;
        this.overallDimension = overallDimension;
        this.drive = drive;
        this.yearOfMake = yearOfMake;
    }
}
