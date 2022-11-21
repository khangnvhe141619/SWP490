package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Favorite;
import com.workshop.carauctionsystem.model.FavoriteDTO;
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

    @Override
    public List<FavoriteDTO> listAllFavorite(int id) {
        return favoriteRepository.listAllFavorite(id);
    }
}
