package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Favorite;
import com.workshop.carauctionsystem.model.FavoriteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM `swp490_cab`.`favorite` WHERE (`id` = :id);")
    public void deleteFavoriteId(int id);
}
