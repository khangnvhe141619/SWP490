package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.repository.UserRepository;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    @ModelAttribute("user")
    public User setUpUser(){
        return new User();
    }

    @Autowired
    UserService service;

    @GetMapping("/default")
    public String successPage(HttpServletRequest request) {
        if(request.isUserInRole("ROLE_Admin"))
            return "redirect:/admin/home";
        return "redirect:/home";
    }

    @Autowired
    UserRepository userRepository;
    @GetMapping("/login")
    public ModelAndView redirectLogin(){
        ModelAndView view = new ModelAndView();
        view.setViewName("Sign-In-Up");
        return view;
    }

    @GetMapping("/logoutPage")
    public ModelAndView getLogout(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie("setUser", null);
        Cookie cookie2 = new Cookie("setUserId", null);
        cookie.setMaxAge(0);
        cookie2.setMaxAge(0);
        response.addCookie(cookie);
        response.addCookie(cookie2);
        HttpSession session = request.getSession();
        session.invalidate();
        return redirectLogin();
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> isUsernameExist(@RequestParam("username") String username,
                                                          @RequestParam("password") String password,
                                                          HttpServletResponse response, HttpSession session, Model model) {
        User u =  service.login(username);
        if(u == null){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Username or password is wrong !", null));
        }
        boolean checkPass = BCrypt.checkpw(password, u.getPassword());
        if(checkPass == false){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Username or password is wrong !", null));
        }
        if(u != null && checkPass){
            if(u.getEnabled() == 0){
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ban", "Account has been banned or not activated !!!", null));
            }
            String avatar = u.getAvatar();
            Cookie cookie = new Cookie("setUser", username);
            String setUserId = String.valueOf(u.getId());
            Cookie cookie2 = new Cookie("setUserId", setUserId);
            cookie.setMaxAge(1 * 60 * 60);
            response.addCookie(cookie);
            response.addCookie(cookie2);
            session.setAttribute("username", username);
            session.setAttribute("avatar", avatar);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("check", true);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("yes", "", null));
        }
        model.addAttribute("invalidCredentials", true);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Username or password is wrong !", null));
    }
}
