package com.workshop.carautionsystem.model;

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
    @Column(name = "brandid")
    private Brand brandId;

    @Column(name = "modelName")
    private String modelName;

    @Column(name = "modelspecificationid")
    private String modelSpecificationId;
}
