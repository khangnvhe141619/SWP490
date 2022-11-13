package com.workshop.carautionsystem.controller;

import com.workshop.carautionsystem.entity.Car;
import com.workshop.carautionsystem.entity.Room;
import com.workshop.carautionsystem.repository.CarRepository;
import com.workshop.carautionsystem.repository.RoomRepository;
import com.workshop.carautionsystem.service.CarService;
import com.workshop.carautionsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import java.util.List;

@RestController
public class HomeController {
    @Autowired
    RoomService service;

    @GetMapping("/home")
    public ModelAndView redirectHome(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model){
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        if(cookie.getValue().equals("")){
            model.addAttribute("check", false);
        } else {
            model.addAttribute("check", true);
        }
        List<Room> listRoom = service.getAllRoom();
        model.addAttribute("listRoom", listRoom);
        ModelAndView view = new ModelAndView();
        view.setViewName("Home");
        return view;
    }
}
