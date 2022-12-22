package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.HistoryBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryBidRepository extends JpaRepository<HistoryBid, Integer> {
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO `swp490_cab`.`historybidprice` (`roomId`, `userId`, `yourBid`, `setTime`) VALUES (:roomId, :userId, :yourBid, :setTime)")
    public void updateHistoryBid(int roomId, int userId, double yourBid, String setTime);

    @Query(nativeQuery = true, value = "SELECT * FROM `swp490_cab`.`historybidprice` WHERE roomId =:roomId  and userId = :userId")
    public List<HistoryBid> getAllByRoomIdAndUserId(int roomId, int userId);

    @Query(nativeQuery = true, value = "SELECT * FROM `swp490_cab`.`historybidprice` WHERE roomId =:roomId")
    public List<HistoryBid> getAllByRoomId(int roomId);
}
