package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.RoomDetailPlayer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

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
class RoomDetailPlayerServiceImplTest {
    @Autowired
    RoomDetailPlayerServiceImpl roomDetailPlayerService;

    @Test
    void givenValidUserIdAndRoomId_thenGetAllPlayer() {
        List<RoomDetailPlayer> lst = roomDetailPlayerService.getAllByUserIdAndRoomId(1, 1);
        assertEquals(0, lst.size());
    }

    @Test
    void addPlayer() {
        roomDetailPlayerService.addPlayer(1, 1, 10, "2022-12-1 10:00", 1);
    }

    @Test
    void updateUserBid() {
        roomDetailPlayerService.updateUserBid(1, "20:00", 1, 1);
    }

    @Test
    void givenValidRoomId_thenGetAllBid() {
        List<RoomDetailPlayer> lst = roomDetailPlayerService.getAllBidByRoomId(1);
        assertEquals(1, lst.size());
    }

    @Test
    void whenValiId_thenWinnerFound() {
        List<RoomDetailPlayer> lst = roomDetailPlayerService.findWinner(1, 2);
        assertEquals(0, lst.size());
    }

    @Test
    void givenValidInformation_whenUpdateWinner_thenSucceed() {
        roomDetailPlayerService.updateWinner(1, 1);
    }

    @Test
    void getAllByUserIdAndWinner() {
        List<RoomDetailPlayer> lst = roomDetailPlayerService.getAllByUserIdAndWinner(2, 1);
        assertEquals(0, lst.size());
    }
}