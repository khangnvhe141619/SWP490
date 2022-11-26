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
@Table(name = "roomdetailplayer")
public class RoomDetailPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "roomid")
    private Room roomId;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User userId;
    @Column(name = "userbid")
    private int userBid;
    @Column(name = "timebid")
    private String timeBid;
    @Column(name = "winner")
    private int winner;
}
