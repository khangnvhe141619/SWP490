package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.entity.RoomParticipant;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.repository.RoomParticipantRepository;
import com.workshop.carauctionsystem.service.RoomParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoomParticipantServiceImpl implements RoomParticipantService {
    @Autowired
    RoomParticipantRepository repository;

    @Override
    public boolean saveParticipant(User u, Room r) {
        RoomParticipant rp = new RoomParticipant();
        rp.setUserId(u);
        rp.setRoomId(r);
        return repository.save(rp) != null ? true : false;
    }

}
