package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.RoomType;
import com.workshop.carauctionsystem.repository.TypeRoomRepository;
import com.workshop.carauctionsystem.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private TypeRoomRepository typeRoomRepo;
    @Override
    public List<RoomType> getAllRoomType() {
        return typeRoomRepo.findAll();
    }
}
