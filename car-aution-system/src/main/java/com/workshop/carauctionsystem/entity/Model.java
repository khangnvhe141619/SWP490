package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "brandid")
    private Brand brandId;

    @Column(name = "modelName")
    private String modelName;

    @OneToOne
    @JoinColumn(name = "modelspecificationid")
    private ModelSpecification modelSpecificationId;
}
