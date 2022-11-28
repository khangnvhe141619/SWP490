package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "carid")
    private Car carId;

    @OneToOne
    @JoinColumn(name = "typeroomid")
    private RoomType typeRoomId;

    @Column(name = "roomname")
    private String roomName;

    @Column(name = "createdat")
    private String createdAt;

    @Column(name = "updatedat")
    private String updatedAt;

    @OneToOne
    @JoinColumn(name = "createdby")
    private User createdBy;

    @Column(name = "opendate")
    private Date openDate;

    @Column(name = "starttime")
    private Time startTime;

    @Column(name = "endtime")
    private Time endTime;

    @Column(name = "ticketnumber")
    private int ticketNumber;

    @Column(name = "ticketprice")
    private int ticketPrice;

    @Column(name = "imgpath")
    private String imgPath;
}
