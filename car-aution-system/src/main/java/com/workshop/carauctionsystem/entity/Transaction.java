package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "carid")
    private Car carId;
    @Column(name = "transactionhash")
    private String transactionHash;
    @Column(name = "status")
    private int status;
    @Column(name = "createdat")
    private Timestamp createdAt;
    @Column(name = "updateat")
    private Timestamp updatedAt;
}
