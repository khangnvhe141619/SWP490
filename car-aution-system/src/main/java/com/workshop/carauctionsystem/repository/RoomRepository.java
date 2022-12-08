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

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(nativeQuery = true, value = " SELECT * FROM room  WHERE room.id = :id")
    public Room findRoomById(int id);

    @Query(nativeQuery = true, value = "select * from room order by id desc")
    public Page<Room> findAllOrderById(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from room where room.roomName like %?1% order by id desc")
    public Page<Room> findAllByName(Pageable pageable, String roomName);

    @Modifying
    @Query(nativeQuery = true, value = "update room set roomname = ?1, starttime = ?2,endtime = ?3,updatedat = ?4, ticketprice = ?5, typeroomid = ?6, createdby = ?7, imgpath = ?8 where id = ?9")
    @Transactional
    public void update(String roomName, String startTime, String endTime, Timestamp updatedAt, int ticketPrice, int typeRoom,int createdBy,String img,int id);

    @Query(nativeQuery = true, value = "SELECT * FROM swp490_cab.room  WHERE current_date() - openDate < 0 or current_time() - startTime < 0")
    public Page<Room> getListRoom(Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT * FROM swp490_cab.room  WHERE current_date() - openDate >= 0 and current_time() - startTime >= 0 and current_time() - endTime <= 0")
    public Page<Room> getListRoomCurrent(Pageable pageable);
    @Query("SELECT r FROM Room as r join r.carId as c join c.modelId as m WHERE c.carName like %?1% and m.modelName like %?2%")
    public Page<Room> searchRoomByNameCar(Pageable pageable,String carName, String model);



}
