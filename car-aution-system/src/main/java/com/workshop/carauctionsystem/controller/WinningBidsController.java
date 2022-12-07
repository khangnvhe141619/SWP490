package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.RoomDetailPlayer;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.service.RoomDetailPlayerService;
import com.workshop.carauctionsystem.service.RoomService;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class WinningBidsController {
    @Autowired
    RoomDetailPlayerService roomDetailPlayerService;

    @Autowired
    RoomService roomService;

    @Autowired
    UserService service;

    @GetMapping("/winningBids")
    public ModelAndView winningBids(@CookieValue(value = "setUserId") int userId, Model model){
        List<RoomDetailPlayer> roomDetailPlayerList = roomDetailPlayerService.getAllByUserIdAndWinner(userId, 1);
        model.addAttribute("roomDetailPlayerList", roomDetailPlayerList);
        User u=  service.findUserById(userId);
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
        ModelAndView view = new ModelAndView();
        view.setViewName("winningBids");
        return view;
    }
}
