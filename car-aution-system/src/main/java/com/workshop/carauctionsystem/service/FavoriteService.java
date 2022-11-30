package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Favorite;
import com.workshop.carauctionsystem.model.FavoriteDTO;

import java.util.List;

public interface FavoriteService {
    public List<FavoriteDTO> listAllFavorite(int id);
    public List<Favorite> listAllFavo(int id);
    public void deleteFavorite(int id);
    public void addFavorite(int userId, int carId, String createdAt);
    public void removeFavorite(int userId, int carId);

}
