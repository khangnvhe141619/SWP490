package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.entity.RoomParticipant;
import com.workshop.carauctionsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomParticipantRepository extends JpaRepository<RoomParticipant, Long> {
    List<User> findByRoomId(long id);
}
