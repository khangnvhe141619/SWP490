package com.workshop.carautionsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class UserController {
    @GetMapping("/login")
    public ModelAndView ridirect(){
        ModelAndView view = new ModelAndView();
        view.setViewName("Sign-In-Up");
        return view;
    }
}
