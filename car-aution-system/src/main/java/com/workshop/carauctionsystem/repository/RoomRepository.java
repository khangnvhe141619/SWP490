package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
