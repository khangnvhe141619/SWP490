package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userid")
    private int userId;
    @OneToMany
    @JoinColumn(name = "carid")
    private List<Car> carId;
    @Column(name = "createdat")
    private Timestamp createdAt;
    @Column(name = "number")
    private int number;
}
