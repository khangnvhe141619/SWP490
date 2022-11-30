package com.workshop.carauctionsystem.entity;

import lombok.*;

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
    @ManyToOne
    @JoinColumn(name = "carid")
    private Car carId;
    @Column(name = "createdat")
    private Timestamp createdAt;
    @Column(name = "number")
    private int number;
}
