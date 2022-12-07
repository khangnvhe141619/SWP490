package com.workshop.carauctionsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminHomeController {
    @GetMapping("/admin/home")
    public ModelAndView showListCar(){
        ModelAndView view = new ModelAndView();
        view = new ModelAndView("admin/index");
        return view;
    }
}
