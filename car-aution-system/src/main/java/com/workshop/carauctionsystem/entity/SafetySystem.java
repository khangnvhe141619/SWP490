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
@Table(name = "safetysystem")
public class SafetySystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "carId")
    private Car carId;
    @Column(name = "air_bad")
    private String air_bad;
    @Column(name = "abs_brake")
    private String abs_brake;
    @Column(name = "speedControl")
    private String speedControl;
    @Column(name = "tirePressure")
    private String tirePressure;
    @Column(name = "otherDescription")
    private String otherdescription;

}
