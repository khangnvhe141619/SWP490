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
@Table(name = "room")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "carid")
    private Car carId;

    @Column(name = "typeroomid")
    private String typeRoomId;

    @Column(name = "roomname")
    private String roomName;

    @Column(name = "roomtime")
    private String roomTime;

    @Column(name = "createdat")
    private String createdAt;

    @Column(name = "updatedat")
    private String updatedAt;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "imgpath")
    private String imgPath;
}
