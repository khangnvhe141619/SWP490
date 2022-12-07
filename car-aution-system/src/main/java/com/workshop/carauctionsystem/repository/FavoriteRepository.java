package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Favorite;
import com.workshop.carauctionsystem.model.FavoriteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM `swp490_cab`.`favorite` WHERE (`userId` = :userId);")
    public List<Favorite> findAllByUserId(int userId);

    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM `swp490_cab`.`favorite` WHERE (`id` = :id);")
    public void deleteFavoriteId(int id);

    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM `swp490_cab`.`favorite` WHERE (`carId` = :carId and `userId` = :userId);")
    public void removeFavoriteId(int carId, int userId);

    @Modifying
    @Query(nativeQuery = true,value = "INSERT INTO `swp490_cab`.`favorite` (`userId`, `carId`, `createdAt`, `number`) VALUES (:userId, :carId, :createdAt, '1')")
    public void addFavorite(int userId, int carId, String createdAt);
}
