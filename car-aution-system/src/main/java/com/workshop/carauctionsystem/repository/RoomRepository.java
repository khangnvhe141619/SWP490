package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(nativeQuery = true, value = " SELECT * FROM room  WHERE room.id = :id")
    public Room findRoomById(int id);

    @Query(nativeQuery = true, value = "select * from room order by id desc")
    public Page<Room> findAllOrderById(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from room where room.roomName like %?1% order by id desc")
    public Page<Room> findAllByName(Pageable pageable, String roomName);

    @Modifying
    @Query(nativeQuery = true, value = "update room set roomname = ?1, starttime = ?2,endtime = ?3,updatedat = ?4, ticketNumber = ?5,ticketprice = ?6, typeroomid = ?7, createdby = ?8, imgpath = ?9 ,openDate = ?10 where id = ?11")
    @Transactional
    public void update(String roomName, String startTime, String endTime, Timestamp updatedAt, int ticketNumber,int ticketPrice, int typeRoom,int createdBy,String img,String date,int id);

    @Query(nativeQuery = true, value = "SELECT * FROM swp490_cab.room  WHERE (case when openDate = current_date() then current_time() - startTime <= 0 else current_date() <openDate end)")
    public Page<Room> getListRoom(Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT * FROM swp490_cab.room  WHERE current_date() - openDate = 0 and current_time() - startTime >= 0 and current_time() - endTime <= 0")
    public Page<Room> getListRoomCurrent(Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM room WHERE DATE(openDate) = ?1")
    public Page<Room> findRoomByCurrent(Pageable pageable,String current);
    @Query(nativeQuery = true,value = "SELECT * FROM room WHERE room.roomName like %?1% AND DATE(openDate) = ?2")
    public Page<Room> findRoomByCurrent(Pageable pageable,String roomName,String current);
    @Query(nativeQuery = true,value = "SELECT * FROM room WHERE DATE(openDate) > ?1")
    public Page<Room> findRoomByPending(Pageable pageable,String current);
    @Query(nativeQuery = true,value = "SELECT * FROM room WHERE room.roomName like %?1% AND DATE(openDate) > ?2")
    public Page<Room> findRoomByPending(Pageable pageable,String roomName,String current);
    @Query(nativeQuery = true,value = "SELECT * FROM room WHERE DATE(openDate) < ?1")
    public Page<Room> findRoomByHistory(Pageable pageable,String current);
    @Query(nativeQuery = true,value = "SELECT * FROM room WHERE room.roomName like %?1% AND DATE(openDate) < ?2")
    public Page<Room> findRoomByHistory(Pageable pageable,String roomName,String current);
    @Modifying
    @Query(nativeQuery = true,value = "update room set openDate = ?1 where id = ?2")
    @Transactional
    public void auctionPause(String date,int id);
    @Query("SELECT r FROM Room as r join r.carId as c join c.modelId as m WHERE c.carName like %?1% and m.modelName like %?2%")
    public Page<Room> searchRoomByNameCar(Pageable pageable,String carName, String model);
    @Query("SELECT r FROM Room as r join r.carId as c join c.modelId as m WHERE c.carName like %?1% ")
    public Page<Room> searchRoomByCar(Pageable pageable,String carName);
    @Query("SELECT r FROM Room as r join r.carId as c join c.modelId as m WHERE m.modelName like %?1%")
    public Page<Room> searchRoomByModel(Pageable pageable, String model);

    @Modifying
    @Query(nativeQuery = true, value = "update room set ticketNumber = ticketNumber - 1 where id = ?1")
    public void updateTicket(int roomId);
}
