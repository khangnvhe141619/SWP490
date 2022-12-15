package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;

@Controller
public class TryAuctionController {
    @Autowired
    UserService userService;

    @GetMapping("/auction")
    public String showAuction(){
        return "index";
    }
    @GetMapping("/connect")
    public String showAuction1(){
        return "connectWallet";
    }
    @GetMapping("/exchange")
    public ModelAndView showAuction1o(@CookieValue(value = "setUser", defaultValue = "") String setUser,
                                      Model model){
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        ModelAndView modelAndView = new ModelAndView();
        if(cookie.getValue().equals("")){
            model.addAttribute("check", false);
        } else {
            model.addAttribute("check", true);
        }
        User u =  userService.findByUsername(setUser);
        modelAndView.addObject("addressWallet", u.getAddressWallet());
        modelAndView.setViewName("exchangeCoins");
        return modelAndView;
    }
}
