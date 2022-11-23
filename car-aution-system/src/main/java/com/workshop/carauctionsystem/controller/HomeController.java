package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.repository.CarRepository;
import com.workshop.carauctionsystem.repository.RoomRepository;
import com.workshop.carauctionsystem.service.BrandService;
import com.workshop.carauctionsystem.service.CarService;
import com.workshop.carauctionsystem.service.RoomService;
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
    @Autowired
    BrandService brandService;

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
        List<Brand> brandList = brandService.getAllBrand();
        model.addAttribute("listRoom", listRoom);
        model.addAttribute("brandList", brandList);
        ModelAndView view = new ModelAndView();
        view.setViewName("Home");
        return view;
    }
}
