package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.ModelSpecification;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.model.ModelSpecieModel;
import com.workshop.carauctionsystem.service.impl.ModelSpecificationServiceImpl;
import com.workshop.carauctionsystem.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ModelSpecificationController {

    @Autowired
    ModelSpecificationServiceImpl modelSpecService;

    @GetMapping("/modelSpec")
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "id") String id,
                                 @RequestParam(defaultValue = "") String search,
                                 Model model) {
        ModelAndView modelAndView = null;
        model.addAttribute("modelSpecieModel", new ModelSpecieModel());
        Page<ModelSpecification> list = modelSpecService.findAllOrderByName(PageRequest.of(page, 5, Sort.by(id)), search);
        if (!list.isEmpty()) {
            modelAndView = new ModelAndView("admin/listModelSpec");
            modelAndView.addObject("modelSpecs", list);
        } else {
            modelAndView = new ModelAndView("page404");
        }
        return modelAndView;
    }


    @PostMapping("modelSpec/create")
    public String create(@ModelAttribute(value = "modelSpecieModel") ModelSpecieModel modelSpecieModel,
                         RedirectAttributes ra) {
        try {
            ModelSpecification modelSpec = new ModelSpecification();
            modelSpec.setNameType(modelSpecieModel.getNameType());
            Validate validateName = new Validate();
            if (validateName.checkDuplicateModelSpec(modelSpecieModel.getNameType(), modelSpecieModel.getSeatNumber(), modelSpecService.getAllModelSpecification())) {
                ra.addFlashAttribute("fail", "ModelSpecification exist");
                return "redirect:/modelSpec";
            }
            modelSpec.setSeatNumber(modelSpecieModel.getSeatNumber());
            modelSpec.setStatus(1);
            modelSpecService.saveModelSpecification(modelSpec);
            ra.addFlashAttribute("success", "The ModelSpecification has been saved successfully");
            return "redirect:/modelSpec";
        } catch (Exception e) {
            ra.addFlashAttribute("fail", "Add ModelSpecification Failed");
            return "redirect:/modelSpec";
        }
    }

    @GetMapping("/modelSpec/delete/{id}")
    public String deleteModelSpec(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
        try {
            modelSpecService.delete(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "page404";
        }
        ra.addFlashAttribute("success", "The ModelSpecification has been deleted successfully");
        return "redirect:/modelSpec";
    }


    @PostMapping("modelSpec/edit")
    public String update(@RequestParam Map<String, String> requestMap,
                         RedirectAttributes ra, Model model) {
        Long id = Long.parseLong(requestMap.get("id"));
        String name = requestMap.get("nameType");
        int seatNumber = Integer.parseInt(requestMap.get("seatNumber"));
        ModelSpecification brandId = modelSpecService.findById(id);
        try {
            Validate validateName = new Validate();
            if (validateName.checkDuplicateModelSpec(name, seatNumber, modelSpecService.getAllModelSpecification())) {
                ra.addFlashAttribute("fail", "ModelSpecification exist");
                return "redirect:/modelSpec";
            }
            modelSpecService.updateModelSpec(name, seatNumber, id);
        } catch (Exception e) {
            ra.addFlashAttribute("fail", "Update ModelSpecification Failed");
            return "redirect:/modelSpec";
        }
        ra.addFlashAttribute("success", "The ModelSpecification has been saved successfully");
        return "redirect:/modelSpec";
    }


}
