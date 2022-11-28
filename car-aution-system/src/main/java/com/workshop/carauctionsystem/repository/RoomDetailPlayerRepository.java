package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.RoomDetailPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomDetailPlayerRepository extends JpaRepository<RoomDetailPlayer, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM roomdetailplayer WHERE roomId =:roomId  and userId = :userId")
    public List<RoomDetailPlayer> findRoomDetailPlayerByRoomIdAndUserId(int roomId, int userId);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO `swp490_cab`.`roomdetailplayer` (`roomId`, `userId`, `userBid`, `timeBid`, `winner`) VALUES (:roomId, :userId, :userBid, :timeBid, :winner)")
    public void addPlayer(int roomId, int userId, int userBid, String timeBid, int winner);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE `swp490_cab`.`roomdetailplayer` SET `userBid` = :bid, `timeBid` = :time WHERE (`userId` = :userId)")
    public void updateUserBid(int bid,String time, int userId);

    @Query(nativeQuery = true, value = "SELECT r.* from roomdetailplayer r where roomId = :roomId ")
    public List<RoomDetailPlayer> getAllBidByRoomId(int roomId);

    @Query(nativeQuery = true, value = "select * FROM roomdetailplayer WHERE userBid = :userBid and roomId = :roomId order by timeBid asc LIMIT 1")
    public List<RoomDetailPlayer> findWinner(int userBid, int roomId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE `swp490_cab`.`roomdetailplayer` SET `winner` = 1 WHERE (`userId` = :winId and `roomId` = :roomId)")
    public void updateWinner(int winId, int roomId);

    @Query(nativeQuery = true, value = "SELECT * FROM `swp490_cab`.`roomdetailplayer` WHERE userId = :userId and winner = :winner")
    public List<RoomDetailPlayer> getAllByUserIdAndWinner(int userId, int winner);
}
