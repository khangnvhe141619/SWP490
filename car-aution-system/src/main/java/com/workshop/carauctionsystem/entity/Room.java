package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
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
    private Timestamp createdAt;

    @Column(name = "updatedat")
    private Timestamp updatedAt;

    @OneToOne
    @JoinColumn(name = "createdby")
    private User createdBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "opendate")
    private String openDate;

    @Column(name = "starttime")
    private String startTime;

    @Column(name = "endtime")
    private String endTime;

    @Column(name = "ticketnumber")
    private int ticketNumber;

    @Column(name = "ticketprice")
    private int ticketPrice;

    @Column(name = "imgpath")
    private String imgPath;

    @Column(name = "roomtime")
    private String roomTime;
}
