package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.repository.CarRepository;
import com.workshop.carauctionsystem.repository.RoomRepository;
import com.workshop.carauctionsystem.service.BrandService;
import com.workshop.carauctionsystem.service.CarService;
import com.workshop.carauctionsystem.service.RoomService;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class HomeController {
    @Autowired
    RoomService service;
    @Autowired
    BrandService brandService;

    @Autowired
    UserService userService;
    @GetMapping("/home")
    public ModelAndView redirectHome(@CookieValue(value = "setUser", defaultValue = "") String setUser,
                                     Model model){
        ModelAndView view = new ModelAndView();
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        if(cookie.getValue().equals("")){
            model.addAttribute("check", false);
        } else {
            User u =  userService.findByUsername(setUser);
            view.addObject("addressWallet", u.getAddressWallet());
            model.addAttribute("check", true);
        }
        List<Brand> brandList = brandService.getAllBrand();
        model.addAttribute("brandList", brandList);
        view.setViewName("Home");
        return view;
    }

    @GetMapping("/selectModel")
    @ResponseBody
    public String isPassExisted(@RequestParam("brandId") int id) {
        System.out.println(id);
        return id+"";
    }
}

