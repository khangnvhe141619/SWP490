package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.repository.UserResponsitory;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

@Controller
public class personProfileController {

    @Autowired
    UserService service;

    @Autowired
    UserResponsitory userResponsitory;

    @GetMapping(value = {"/personProfile"})
    public String getPersonProfile( User user, @CookieValue(value = "setUser", defaultValue = "") String setUser, Model model){
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        Optional<User> u=  service.findUserByName(setUser);
        if(u.isPresent()){
            String name = u.get().getFullName();
            String email = u.get().getEmail();
            String phone = u.get().getPhone();
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("phone", phone);
        }
        return "PeronalProfile";
    }
}
