package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.entity.Room;
import com.workshop.carautionsystem.repository.RoomRepository;
import com.workshop.carautionsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }
}
