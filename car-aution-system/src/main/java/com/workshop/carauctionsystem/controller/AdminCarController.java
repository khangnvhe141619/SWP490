package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.model.CarDTO;
import com.workshop.carauctionsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminCarController {
    @Autowired
    CarService carService;
//    @GetMapping("/admin/car")
//    public String showCar(){
//        return "admin/listCar";
//    }

    @GetMapping("/admin/car")
    public ModelAndView showListCar(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "id") String id,
                                    @RequestParam(defaultValue = "") String search, Model model) {
        ModelAndView view = new ModelAndView();
        model.addAttribute("car", new CarDTO());
        Page<Car> lstCar = carService.findAllByCarName(PageRequest.of(page, 5, Sort.by(id)), search);
        if (!lstCar.isEmpty()) {
            view = new ModelAndView("admin/listCar");
            view.addObject("cars", lstCar);
        } else {
            view = new ModelAndView("admin/listCar");
            view.addObject("lst_empty", "List Empty!");
        }
        return view;
    }
}
