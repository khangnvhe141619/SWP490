package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.RoomDetailPlayer;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import java.util.List;

@RestController
public class MyWalletController {
    @Autowired
    UserService service;

    @GetMapping("/wallet")
    public ModelAndView getWallet(@CookieValue(value = "setUserId") int setUserId,
                                  @CookieValue(value = "setUser") String setUser, Model model) {
        User u = service.findUserById(setUserId);
        if (u != null) {
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
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        if (cookie.getValue().equals("")) {
            model.addAttribute("check", false);
        } else {
            model.addAttribute("check", true);
        }
        ModelAndView modelAndView = new ModelAndView();
        String _address = u.getAddressWallet().substring(0, 4) + "..." + u.getAddressWallet().substring(u.getAddressWallet().length() - 4, u.getAddressWallet().length());
        modelAndView.addObject("addressWallet", _address);
        modelAndView.setViewName("myWallet");
        return modelAndView;
    }
}
