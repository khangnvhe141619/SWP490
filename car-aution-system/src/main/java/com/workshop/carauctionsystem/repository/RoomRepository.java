package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(nativeQuery = true, value = " SELECT * FROM room  WHERE room.id = :id")
    public Room findRoomById(int id);
}
