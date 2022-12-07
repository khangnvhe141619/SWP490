package com.workshop.carauctionsystem.model;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.entity.RoomType;
import com.workshop.carauctionsystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {
    private int id;
    private Car carId;
    private RoomType typeRoomId;
    private String roomName;
    private String roomTime;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private User createdBy;
    private String openDate;
    private String startTime;
    private String endTime;
    private int ticketNumber;
    private int ticketPrice;
    private String imgPath;
}
