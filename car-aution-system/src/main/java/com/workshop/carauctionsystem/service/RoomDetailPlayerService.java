package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.RoomDetailPlayer;

import java.util.List;

public interface RoomDetailPlayerService {
    public List<RoomDetailPlayer> getAllByUserIdAndRoomId(int roomId, int userId);
    public void addPlayer(int roomId, int userId, int userBid, String timeBid, int winner);
    public void updateUserBid(int bid, String time,int userId, int roomId);
    public List<RoomDetailPlayer> getAllBidByRoomId(int roomId);
    public List<RoomDetailPlayer> findWinner(int userBid, int roomId);
    public void updateWinner(int winId, int roomId);
    public List<RoomDetailPlayer> getAllByUserIdAndWinner(int userId, int winner);
}
