package com.workshop.carauctionsystem.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @ManyToOne
    @JoinColumn(name = "carid")
    @Fetch(FetchMode.JOIN)
    private Car carId;
    @Column(name = "air_bad")
    private String air_bag;
    @Column(name = "abs_brake")
    private String abs_brake;
    @Column(name = "speedcontrol")
    private String speedControl;
    @Column(name = "tirepressure")
    private String tirePressure;
    @Column(name = "otherdescription")
    private String otherdescription;

}
