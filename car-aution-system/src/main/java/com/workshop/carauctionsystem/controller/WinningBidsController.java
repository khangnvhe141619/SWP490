package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.RoomDetailPlayer;
import com.workshop.carauctionsystem.service.RoomDetailPlayerService;
import com.workshop.carauctionsystem.service.RoomService;
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

    @GetMapping("/winningBids")
    public ModelAndView winningBids(@CookieValue(value = "setUserId") int userId, Model model){
        List<RoomDetailPlayer> roomDetailPlayerList = roomDetailPlayerService.getAllByUserIdAndWinner(userId, 1);
        model.addAttribute("roomDetailPlayerList", roomDetailPlayerList);
        ModelAndView view = new ModelAndView();
        view.setViewName("winningBids");
        return view;
    }
}
