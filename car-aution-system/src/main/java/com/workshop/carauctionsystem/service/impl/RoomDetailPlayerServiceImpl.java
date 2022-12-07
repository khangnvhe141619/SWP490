package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.RoomDetailPlayer;
import com.workshop.carauctionsystem.repository.RoomDetailPlayerRepository;
import com.workshop.carauctionsystem.service.RoomDetailPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomDetailPlayerServiceImpl implements RoomDetailPlayerService {
    @Autowired
    RoomDetailPlayerRepository roomDetailPlayerRepository;
    @Override
    public List<RoomDetailPlayer> getAllByUserIdAndRoomId(int roomId, int userId) {
        return roomDetailPlayerRepository.findRoomDetailPlayerByRoomIdAndUserId(roomId, userId);
    }

    @Override
    public void addPlayer(int roomId, int userId, int userBid, String timeBid, int winner) {
        roomDetailPlayerRepository.addPlayer(roomId, userId, userBid, timeBid, winner);
    }

    @Override
    public void updateUserBid(int bid, String time,int userId, int roomId){
        roomDetailPlayerRepository.updateUserBid(bid, time,userId, roomId);
    }

    @Override
    public List<RoomDetailPlayer> getAllBidByRoomId(int roomId) {
        return roomDetailPlayerRepository.getAllBidByRoomId(roomId);
    }

    @Override
    public List<RoomDetailPlayer> findWinner(int userBid, int roomId) {
        return roomDetailPlayerRepository.findWinner(userBid, roomId);
    }

    @Override
    public void updateWinner(int winId, int roomId) {
        roomDetailPlayerRepository.updateWinner(winId, roomId);
    }

    @Override
    public List<RoomDetailPlayer> getAllByUserIdAndWinner(int userId, int winner) {
        return roomDetailPlayerRepository.getAllByUserIdAndWinner(userId, winner);
    }
}
