package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.repository.UserRepository;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class UserController {

    @ModelAttribute("user")
    public User setUpUser(){
        return new User();
    }

    @Autowired
    UserService service;

    @Autowired
    UserRepository userRepository;
    @GetMapping("/login")
    public ModelAndView redirectLogin(){
        ModelAndView view = new ModelAndView();
        view.setViewName("Sign-In-Up");
        return view;
    }

    @GetMapping(value = {"/logout"})
    public ModelAndView getLogout(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie("setUser", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return redirectLogin();
    }

    @PostMapping(value = {"/login"})
    public ModelAndView login(@ModelAttribute(name = "setUser") User user, Model model, @CookieValue(value = "setUser", defaultValue = "") String setUser,
                              HttpServletRequest request, HttpServletResponse response){
        Optional<User> u =  service.login(user.getUserName(),user.getPassword());
        System.out.println(u);
        ModelAndView view = new ModelAndView();
        if(u.isPresent()){
            setUser = user.getUserName();
            Cookie cookie = new Cookie("setUser", setUser);
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("check", true);
            view.setViewName("redirect:/home");
            return view;
        }
        model.addAttribute("invalidCredentials", true);
        view.setViewName("Sign-In-Up");
        return view;
    }
}
