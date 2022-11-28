package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Room;

import java.util.List;

public interface RoomService {
    public List<Room> getAllRoom();
    public List<Room> getAllRoomLimit();
    public Room getRoomById(int roomId);
    public List<Room> getAllRoomByPage(int page);
}
