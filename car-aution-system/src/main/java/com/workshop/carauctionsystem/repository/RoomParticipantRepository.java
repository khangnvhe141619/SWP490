package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.entity.RoomParticipant;
import com.workshop.carauctionsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;

public interface RoomParticipantRepository extends JpaRepository<RoomParticipant, Long> {
    List<User> findByRoomId(long id);
    @Query(nativeQuery = true,value = "SELECT * FROM roomparticipant where roomparticipant.roomid = ?1 and roomparticipant.userid = ?2")
    RoomParticipant findAllByUserIdAndRoomId(long rId, long uId);
}
