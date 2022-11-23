package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "model")
public class ModelCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "brandid")
    private Brand brandId;

    @Column(name = "modelname")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Name not special characters")
    private String modelName;

    @OneToOne
    @JoinColumn(name = "modelspecificationid")
    private ModelSpecification modelSpecificationId;

    @Column(name = "[status]")
    private int status;

}
