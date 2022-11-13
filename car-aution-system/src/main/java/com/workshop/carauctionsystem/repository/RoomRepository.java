package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
