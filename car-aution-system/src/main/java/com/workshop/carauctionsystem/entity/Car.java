package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carname")
    private String carName;

    @Column(name = "[description]")
    private String description;

    @Column(name = "upboundprice")
    private Long upBoundPrice;

    @Column(name = "downboundprice")
    private Long downBoundPrice;

    @OneToOne
    @JoinColumn(name = "modelid")
    private ModelCar modelId;

    @Column(name = "status")
    private int status;

    @OneToOne
    @JoinColumn(name = "createdby")
    private User createdBy;

    @Column(name = "createdat")
    private Timestamp createdAt;

    @Column(name = "updatedat")
    private Timestamp updatedAt;

    @OneToOne(targetEntity = CarSpecification.class, mappedBy = "carId",orphanRemoval = false,fetch = FetchType.LAZY)
    private CarSpecification  carSpec;

    @OneToOne(targetEntity = SafetySystem.class, mappedBy = "carId",orphanRemoval = false,fetch = FetchType.LAZY)
    private SafetySystem safeSys;

    @OneToMany(targetEntity = Image.class, mappedBy = "carId",orphanRemoval = false,fetch = FetchType.LAZY)
    private Set<Image> imgCar;
}
