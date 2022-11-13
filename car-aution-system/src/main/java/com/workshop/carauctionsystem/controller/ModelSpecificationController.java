package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.ModelSpecification;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.service.ModelSpecificationService;
import com.workshop.carauctionsystem.validate.Validate1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller
public class ModelSpecificationController {

    @Autowired
    ModelSpecificationService modelSpecService;

    @GetMapping("/modelSpec")
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String id) {
        ModelAndView modelAndView = null;
        Page<ModelSpecification> list = modelSpecService.findAllOrderById(PageRequest.of(page, 5, Sort.by(id)));
        if (!list.isEmpty()) {
            modelAndView = new ModelAndView("listModelSpec");
            modelAndView.addObject("modelSpecs", list);
        } else {
            modelAndView = new ModelAndView("page404");
        }
        return modelAndView;
    }

    @GetMapping("/modelSpec/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = null;
        try {
            modelAndView = new ModelAndView("fromCreateModelSpec");
            modelAndView.addObject("modelSpecs", new ModelSpecification());
            return modelAndView;
        } catch (Exception e) {
            modelAndView = new ModelAndView("page404");
            return modelAndView;
        }
    }

    @PostMapping("modelSpec/create")
    public String create(@Valid @ModelAttribute(value = "modelSpec") ModelSpecification modelSpec,
                         RedirectAttributes ra, Model model) {
        try {
            Validate1 validateName = new Validate1();
            if( validateName.checkDuplicateModelSpec(modelSpec.getNameType(), modelSpec.getSeatNumber(), modelSpecService.getAllModelSpecification())){
                model.addAttribute("message","ModelSpecification exist");
                return "fromCreateModelSpec";
            }
            modelSpecService.saveModelSpecification(modelSpec);
            ra.addFlashAttribute("message", "The ModelSpecification has been saved successfully");
            return "redirect:/modelSpecs";
        } catch (Exception e) {
            model.addAttribute("message", "Add ModelSpecification Failed");
            return "page404";
        }
    }

    @GetMapping("/modelSpec/delete/{id}")
    public String deleteModelSpec(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
        try {
            modelSpecService.delete(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/brand";
    }

    @GetMapping("/modelSpec/edit/{id}")
    public String showFormUpdate(@PathVariable(value = "id") Long id, Model model, RedirectAttributes ra) throws NotFoundException {
        ModelSpecification modelSpec = modelSpecService.findById(id);
        if (modelSpec != null) {
            model.addAttribute("modelSpec", modelSpec);
            return "fromUpdateModelSpec";
        } else {
            throw new NotFoundException("Could not find any with ID");
        }
    }

    @PostMapping("modelSpec/edit")
    public String update(@Valid @ModelAttribute(value = "modelSpec") ModelSpecification modelSpec,
                         BindingResult bindingResult, @RequestParam Long id,
                         RedirectAttributes ra,Model model) {
        try {
            Validate1 validateName = new Validate1();
            if( validateName.checkDuplicateModelSpec(modelSpec.getNameType(), modelSpec.getSeatNumber(), modelSpecService.getAllModelSpecification())){
                model.addAttribute("message","ModelSpecification exist");
                return "fromCreateModelSpec";
            }
            modelSpecService.saveModelSpecification(modelSpec);
        } catch (Exception e) {
            model.addAttribute("message", "Add ModelSpecification Failed");
            return "page404";
        }
        ra.addFlashAttribute("message", "The ModelSpecification has been saved successfully");
        return "redirect:/modelSpecs";
    }


}
