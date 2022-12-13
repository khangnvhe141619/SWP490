package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.model.FavoriteDTO;
import com.workshop.carauctionsystem.repository.FavoriteDTORepository;
import com.workshop.carauctionsystem.repository.FavoriteRepository;
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
class FavoriteServiceImplTest {
    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    FavoriteDTORepository favoriteDTORepository;
    @Autowired
    FavoriteServiceImpl favoriteService;

    @Test
    void whenValidIDUser_thenGetlistAllFavorite() {
        List<FavoriteDTO> lst = favoriteService.listAllFavorite(1);
        assertEquals(2, lst.size());
    }

    @Test
    void whenValidIDUser_thenGetlistAllFavorite_2() {
        List<FavoriteDTO> lst = favoriteService.listAllFavorite(1);
        assertEquals(2, lst.size());
    }

    @Test
    void givenValidId_whenDeleteFavorite_thenSucceed() {
        favoriteService.deleteFavorite(1);
    }

    @Test
    void givenCar_whenAddFavorite_thenSucceed() {
        favoriteService.addFavorite(1, 1, "2022-12-10");
    }

    @Test
    void givenValidId_whenRemoveFavorite_thenSucceed() {
        favoriteService.removeFavorite(1, 1);
    }
}