package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;

@RestController
public class GuideLineController {
    @Autowired
    UserService userService;
    @GetMapping("/guideLine")
    public ModelAndView guideLine(@CookieValue(value = "setUser", defaultValue = "") String setUser,
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
        view.addObject("activeG", "nav-link scrollto active");
        view.setViewName("guideline");
        return view;
    }
}
