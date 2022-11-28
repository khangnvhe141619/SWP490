package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Modifying
    @Query(nativeQuery = true, value = " SELECT * FROM room LIMIT 3")
    public List<Room> findAllLimit();
    @Query(nativeQuery = true, value = " SELECT * FROM room  WHERE room.id = :id")
    public Room findRoomById(int id);

    @Modifying
    @Query(nativeQuery = true, value = "with x as (SELECT ROW_NUMBER() OVER (ORDER BY r.createdAt) AS STT, r.* FROM swp490_cab.room r) select x.* from x where STT between :page*3-2 and :page*3")
    public List<Room> getAllRoomByTime(int page);
}
