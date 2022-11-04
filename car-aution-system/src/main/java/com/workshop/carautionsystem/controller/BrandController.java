package com.workshop.carautionsystem.controller;


import com.workshop.carautionsystem.model.Brand;
import com.workshop.carautionsystem.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class BrandController {

    @Autowired
    BrandService brandService;

    //display list brand by page
    @GetMapping("/brand")
    public ModelAndView showListBrand(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("brands", brandService.findAll(PageRequest.of(page, 5)));
        return modelAndView;
    }

    @GetMapping("/brand/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("brand", new Brand());
        return modelAndView;
    }

    @PostMapping("/brand/create")
    public ModelAndView create(@Valid @ModelAttribute("brand") Brand brand) {
            brandService.saveBrand(brand);
            ModelAndView modelAndView = new ModelAndView("redirect:/brand");
            return modelAndView;

    }

}
