package com.workshop.carautionsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "carname")
    private String carName;

    @Column(name = "description")
    private String description;

    @Column(name = "upbroundprice")
    private Long upBroundPrice;

    @Column(name = "downbroundprice")
    private Long downBroundPrice;

    @OneToOne
    @Column(name = "modelid")
    private Model modelId;

    @Column(name = "status")
    private String status;

    @OneToOne
    @Column(name = "createby")
    private User createBy;

    @Column(name = "createat")
    private Timestamp createAt;

    @Column(name = "updateat")
    private Timestamp updateAt;

}
