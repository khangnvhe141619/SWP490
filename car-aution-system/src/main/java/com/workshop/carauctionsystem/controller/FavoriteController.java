package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Favorite;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.model.FavoriteDTO;
import com.workshop.carauctionsystem.service.FavoriteService;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    UserService service;

    @GetMapping("/myFavorite")
    public ModelAndView redirectFavorite(@CookieValue(value = "setUserId") int setUserId, Model model){
        ModelAndView view = new ModelAndView();
        List<FavoriteDTO> favorite = favoriteService.listAllFavorite(setUserId);
        User u=  service.findUserById(setUserId);
        if(u != null){
            String name = u.getFullName();
            String email = u.getEmail();
            String phone = u.getPhone();
            String username = u.getUserName();
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("phone", phone);
            model.addAttribute("username", username);
            model.addAttribute("INFOR", u);
        }
        model.addAttribute("listFavorite", favorite);
        view.setViewName("myFavorite");
        return view;
    }

    @GetMapping("/myFavorite/{id}")
    public ModelAndView redirectUnFavorite(@PathVariable("id") int id){
        favoriteService.deleteFavorite(id);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/myFavorite");
        return view;
    }
}
