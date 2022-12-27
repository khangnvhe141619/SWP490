package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface RoomService {
    public List<Room> getAllRoom();
    public Room getRoomById(int roomId);
    public Page<Room> findRoomByCurrent(Pageable pageable,String roomName,String current);
    public Page<Room> findRoomByPending(Pageable pageable,String roomName,String current);
    public Page<Room> findRoomByHistory(Pageable pageable,String roomName,String current);
    public Page<Room> findAllByName(Pageable pageable, String roomName);
    public void saveRoom(Room room);
    public void update(String roomName, String startTime, String endTime, Timestamp updatedAt, int ticketNumber,int ticketPrice, int typeRoom, int createdBy, String img,String date, int id);
    public void auctionPause (String date,int id);
    public void delete(Long id) throws NotFoundException;
    public Page<Room> getListRoom(int pageNo, int pageSize);
    public Page<Room> getListRoomCurrent(int pageNo, int pageSize);
    public Page<Room> getSearchRoom(Pageable pageable,String carName,String model);

    public void updateTicket(Room room);

    public List<Integer> showAllBidChart(int id);
}
