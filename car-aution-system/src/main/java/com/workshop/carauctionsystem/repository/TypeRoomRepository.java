package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRoomRepository extends JpaRepository<RoomType,Integer> {

}
