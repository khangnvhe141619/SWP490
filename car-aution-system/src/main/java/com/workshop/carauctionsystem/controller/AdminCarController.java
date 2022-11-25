package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.model.CarDTO;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.service.BrandService;
import com.workshop.carauctionsystem.service.CarService;
import com.workshop.carauctionsystem.service.ModelService;

import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminCarController {
    @Autowired
    CarService carService;

    @Autowired
    BrandService brandService;

    @Autowired
    ModelService modelService;

    @Autowired
    private UserService userService;


    @GetMapping("/admin/car")
    public ModelAndView showListCar(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "id") String id,
                                    @RequestParam(defaultValue = "") String search, Model model) {
        ModelAndView view = new ModelAndView();
        model.addAttribute("car", new CarDTO());

        model.addAttribute("brands", brandService.getAllBrand());

        model.addAttribute("modelCar", modelService.getAllModelByStatus());
        model.addAttribute("createBy", userService.getRoleByAdminCar());


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

    @GetMapping("/car/all")
    public ResponseEntity<ResponseObject> getCar(){
        List<Car> lc=carService.findAllDTO();
        if (!lc.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Existed!", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Valid!", null));
    }

}
