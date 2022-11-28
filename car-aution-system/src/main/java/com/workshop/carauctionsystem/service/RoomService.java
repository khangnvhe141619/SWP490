package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomService {
    public List<Room> getAllRoom();
    public Room getRoomById(int roomId);

    public Page<Room> findAllByName(Pageable pageable, String roomName);
}
