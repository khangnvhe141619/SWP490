package com.workshop.carauctionsystem.model;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.entity.RoomType;
import com.workshop.carauctionsystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {
    private int id;
    private Car carId;
    private RoomType typeRoomId;
    private String roomName;
    private String createdAt;
    private String updatedAt;
    private User createdBy;
    private Date openDate;
    private Time startTime;
    private Time endTime;
    private int ticketNumber;
    private int ticketPrice;
    private String imgPath;
}
