package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.repository.RoomRepository;
import com.workshop.carauctionsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(int roomId) {
        return roomRepository.findRoomById(roomId);
    }

    @Override
    public Page<Room> findAllByName(Pageable pageable, String roomName) {
        if (roomName != null) {
            return roomRepository.findAllByName(pageable, roomName);
        }else {
            return roomRepository.findAllOrderById(pageable);
        }
    }

    @Override
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void update(String roomName, String startTime, String endTime, Timestamp updatedAt, int ticketPrice, int typeRoom, int createdBy, String img, int id) {
        roomRepository.update(roomName,startTime,endTime,updatedAt,ticketPrice,typeRoom,createdBy,img,id);
    }

       @Override
    public void delete(Long id) throws NotFoundException {
//        Long count = roomRepository.countById(id);
//        if (count == null || count == 0) {
//            throw new NotFoundException("Could not find any with ID" + id);
//        }
//        roomRepository.delete(id);
    }

    @Override
    public Page<Room> getListRoom(int pageNo, int pageSize) {
        Pageable roomPage = PageRequest.of(pageNo-1,pageSize);
        return roomRepository.getListRoom(roomPage);
    }
    @Override
    public Page<Room> getListRoomCurrent(int pageNo, int pageSize) {
        Pageable roomPage = PageRequest.of(pageNo-1,pageSize);
        return roomRepository.getListRoomCurrent(roomPage);
    }

    @Override
    public Page<Room> getSearchRoom(Pageable pageable, String carName, String model) {
        if(carName != null || model != null){
            return roomRepository.searchRoomByNameCar(pageable,carName,model);
        }else{
            return roomRepository.getListRoom(pageable);
        }
    }
}
