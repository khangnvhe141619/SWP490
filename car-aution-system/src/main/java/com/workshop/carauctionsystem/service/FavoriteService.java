package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Favorite;
import com.workshop.carauctionsystem.model.FavoriteDTO;

import java.util.List;

public interface FavoriteService {
    public List<FavoriteDTO> listAllFavorite(int id);
}
