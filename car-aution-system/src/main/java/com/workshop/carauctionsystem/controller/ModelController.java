package com.workshop.carauctionsystem.controller;

import com.workshop.carautionsystem.entity.ModelCar;
import com.workshop.carautionsystem.entity.ModelSpecification;
import com.workshop.carautionsystem.exception.NotFoundException;
import com.workshop.carautionsystem.service.impl.ModelServiceImpl;
import com.workshop.carautionsystem.validate.Validate;
import com.workshop.carautionsystem.validate.Validate1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ModelController {
    @Autowired
    ModelServiceImpl modelService;

    @Autowired
    Validate validate;

    @GetMapping("/model")
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String id) {
        ModelAndView modelAndView = null;
        Page<ModelCar> list = modelService.findAllOrderById(PageRequest.of(page, 5, Sort.by(id)));
        if (!list.isEmpty()) {
            modelAndView = new ModelAndView("ListModel");
            modelAndView.addObject("models", list);
        } else {
            modelAndView = new ModelAndView("page404");
        }
        return modelAndView;
    }

    @GetMapping("/model/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = null;
        try {
            modelAndView = new ModelAndView("showFromCreat");
            modelAndView.addObject("model", new ModelCar());
            return modelAndView;
        } catch (Exception e) {
            modelAndView = new ModelAndView("page404");
            return modelAndView;
        }
    }

    @PostMapping("model/create")
    public String create(@Valid @ModelAttribute(value = "model") ModelCar modelCar,
                         @RequestParam Long idModelSpec,
                         RedirectAttributes ra, Model model) {
        try {
            Validate1 validateName = new Validate1();
            if( validateName.checkDuplicateModel(modelCar.getModelName(),
                    modelCar.getBrandId().getId(), modelService.getAllModel())){
                model.addAttribute("message","Model exist");
                return "fromCreateModelSpec";
            }
            modelService.saveModel(modelCar);
        } catch (Exception e) {
            model.addAttribute("message", "Add New Car Model Failed");
            return "page404";
        }
        ra.addFlashAttribute("message", "The Car Model has been saved successfully");
        return "redirect:/modelSpecs";
    }


    @GetMapping("/model/delete/{id}")
    public String deleteModel(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
        try {
            modelService.delete(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/model";
    }

    @GetMapping("/model/edit/{id}")
    public String showFormUpdate(@PathVariable(value = "id") Long id, Model model, RedirectAttributes ra) throws NotFoundException {
        ModelCar modelCar = modelService.findById(id);
        if (modelCar != null) {
            model.addAttribute("models", modelCar);
            return "fromUpdateModelSpec";
        } else {
            throw new NotFoundException("Could not find any with ID");
        }
    }

    @PostMapping("model/edit")
    public String update(@Valid @ModelAttribute(value = "model") ModelCar modelCar,
                         @RequestParam Long id, RedirectAttributes ra,Model model) {
        try {
            Validate1 validateName = new Validate1();
            if( validateName.checkDuplicateModel(modelCar.getModelName(), modelCar.getBrandId().getId(),
                    modelService.getAllModel())){
                model.addAttribute("message","Model exist");
                return "fromCreateModelSpec";
            }
            modelService.saveModel(modelCar);
        } catch (Exception e) {
            model.addAttribute("message", "Add New Car Model Failed");
            return "page404";
        }
        ra.addFlashAttribute("message", "The Car Model has been saved successfully");
        return "redirect:/modelSpecs";
    }
}
