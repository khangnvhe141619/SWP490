package com.workshop.carauctionsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GuideLineController {
    @GetMapping("/guideLine")
    public ModelAndView guideLine(){
        ModelAndView view = new ModelAndView();
        view.setViewName("guideline");
        return view;
    }
}
