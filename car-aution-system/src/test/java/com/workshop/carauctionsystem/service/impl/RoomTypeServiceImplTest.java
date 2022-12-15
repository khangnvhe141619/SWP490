package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.RoomType;
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
class RoomTypeServiceImplTest {
    @Autowired
    RoomTypeServiceImpl roomTypeService;

    @Test
    void whenSearch_thenListFound() {
        List<RoomType> lst = roomTypeService.getAllRoomType();
        assertEquals(2, lst.size());
    }
}