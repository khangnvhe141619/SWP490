package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Favorite;
import com.workshop.carauctionsystem.model.FavoriteDTO;
import com.workshop.carauctionsystem.repository.FavoriteDTORepository;
import com.workshop.carauctionsystem.repository.FavoriteRepository;
import com.workshop.carauctionsystem.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    FavoriteDTORepository favoriteDTORepository;

    @Override
    public List<FavoriteDTO> listAllFavorite(int id) {
        return favoriteDTORepository.listAllFavorite(id);
    }

    @Override
    public void deleteFavorite(int id) {
        favoriteRepository.deleteFavoriteId(id);
    }
}
