package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.entity.RoomType;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@Rollback(value = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {
        "com.workshop.carauctionsystem.repository",
        "com.workshop.carauctionsystem.service"
})
class RoomServiceImplTest {
    @Autowired
    RoomServiceImpl roomService;

    @Test
    void getAllRoom() {
        List<Room> lst = roomService.getAllRoom();
        assertEquals(9, lst.size());
    }

    @Test
    void whenValiId_thenRoomFound() {
        roomService.getRoomById(1);
    }

    @Test
    void whenSearch_thenDisplayListRoomCurrent_1() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<Room> pr = roomService.findRoomByCurrent(pageable, null, null);
        assertEquals(0, pr.getNumberOfElements());
    }

    @Test
    void whenSearch_thenDisplayListRoomPending() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<Room> pr = roomService.findRoomByPending(pageable, null, null);
        assertEquals(0, pr.getNumberOfElements());
    }

    @Test
    void whenSearch_thenDisplayListRoomHistory() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<Room> pr = roomService.findRoomByHistory(pageable, null, null);
        assertEquals(0, pr.getNumberOfElements());
    }

    @Test
    void givenValidName_whenSearch_thenDisplayListRoom() {
        String name = "Lux";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<Room> pr = roomService.findAllByName(pageable, name);
        assertEquals(1, pr.getNumberOfElements());
        Page<Room> pr1 = roomService.findAllByName(pageable, null);
        assertEquals(5, pr1.getNumberOfElements());
    }

    @Test
    void givenValidInformation_whenSave_thenSucceed() {
        Car c = new Car();
        c.setId(1l);
        RoomType roomType = new RoomType();
        roomType.setId(1);
        User u = new User();
        u.setId(1);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Room r = new Room(1, c, roomType, "Lux A", timestamp, timestamp, u,
                "2022-12-10", "20:00", "20:10", 10, 200, "hoang/img1.jpg", "20:00");
        roomService.saveRoom(r);
    }

    @Test
    void givenValidInformation_whenUpdate_thenSucceed() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        roomService.update("Lux A", "20:00", "20:20", timestamp,
                10, 1, 1, 1,"hoang/img1.jpg", 1);
    }

    @Test
    void givenValidInformation_whenDelete_thenSucceed() throws NotFoundException {
        roomService.delete(1l);
    }

    @Test
    void whenSearch_thenDisplayListRoom() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<Room> pr = roomService.getListRoom(1, 5);
        assertEquals(0, pr.getNumberOfElements());
    }

    @Test
    void whenSearch_thenDisplayListRoomCurrent() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<Room> pr = roomService.getListRoomCurrent(1, 5);
        assertEquals(0, pr.getNumberOfElements());
    }

    @Test
    void givenValidName_whenSearch_thenListFound() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<Room> pr = roomService.getSearchRoom(pageable, null, null);
        assertEquals(0, pr.getNumberOfElements());
        Page<Room> pr1 = roomService.getSearchRoom(pageable, "Lux", "LX");
        assertEquals(0, pr.getNumberOfElements());
    }
}