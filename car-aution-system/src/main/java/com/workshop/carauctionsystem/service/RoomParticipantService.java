package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.entity.RoomParticipant;
import com.workshop.carauctionsystem.entity.User;

public interface RoomParticipantService {
    public boolean saveParticipant(User u, Room r);

    public boolean isParticipantIn(User u, Room r);
}
