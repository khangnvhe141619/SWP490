package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Favorite;
import com.workshop.carauctionsystem.model.FavoriteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteDTO, Integer> {
    @Modifying
    @Query(nativeQuery = true, value = "select f.*, R.carName, R.imgPath from favorite f JOIN\n" +
            "(select c.carName, i.imgPath, i.carId from car c join image i on i.id = (\n" +
            "    select id from image \n" +
            "    where image.carId = c.id\n" +
            "    limit 1\n" +
            ")) AS R \n" +
            "ON f.carId = R.carId WHERE userId = :id")
    public List<FavoriteDTO> listAllFavorite(int id);
}
