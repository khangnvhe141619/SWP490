package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Favorite;
import com.workshop.carauctionsystem.model.FavoriteDTO;
import com.workshop.carauctionsystem.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    @GetMapping("/myFavorite")
    public ModelAndView redirectFavorite(@CookieValue(value = "setUserId") int setUserId, Model model){
        ModelAndView view = new ModelAndView();
        List<FavoriteDTO> favorite = favoriteService.listAllFavorite(setUserId);
        model.addAttribute("listFavorite", favorite);
        view.setViewName("myFavorite");
        return view;
    }
}
