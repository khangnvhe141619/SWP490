package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.repository.UserRepository;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
                              HttpServletRequest request, HttpServletResponse response, HttpSession session){
        User u =  service.login(user.getUserName());
        ModelAndView view = new ModelAndView();
        boolean checkPass = BCrypt.checkpw(user.getPassword(), u.getPassword());
        if(u != null && checkPass){
            if(u.getEnabled() == 0){
                model.addAttribute("checkUserBan", true);
                view.setViewName("Sign-In-Up");
                return view;
            }
            setUser = user.getUserName();
            String avatar = u.getAvatar();
            Cookie cookie = new Cookie("setUser", setUser);
            String setUserId = String.valueOf(u.getId());
            Cookie cookie2 = new Cookie("setUserId", setUserId);
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            response.addCookie(cookie2);
            session.setAttribute("username", setUser);
            session.setAttribute("avatar", avatar);
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
