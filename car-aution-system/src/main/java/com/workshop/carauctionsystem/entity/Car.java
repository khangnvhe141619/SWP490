package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    @Column(name = "upboundprice")
    private Long upBounPprice;

    @Column(name = "downboundprice")
    private Long downBoundPrice;

    @OneToOne
    @JoinColumn(name = "modelid")
    private Model modelId;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "createdby")
    private User createdBy;

    @Column(name = "createdat")
    private Timestamp createdAt;

    @Column(name = "updatedat")
    private Timestamp updatedAt;
}
