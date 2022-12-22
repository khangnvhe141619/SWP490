package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "historyBidPrice")
public class HistoryBid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "roomid")
    private Room roomId;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User userId;
    @Column(name = "yourbid")
    private double yourBid;
    @Column(name = "settime")
    private String setTime;

}

