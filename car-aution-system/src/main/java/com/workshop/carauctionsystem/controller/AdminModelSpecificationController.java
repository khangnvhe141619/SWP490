package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.ModelSpecification;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.model.ModelSpecieDTO;
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

import java.util.Map;

@Controller
public class AdminModelSpecificationController {

    @Autowired
    ModelSpecificationServiceImpl modelSpecService;

    @GetMapping("/admin/modelSpec")
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "id") String id,
                                 @RequestParam(defaultValue = "") String search,
                                 Model model) {
        ModelAndView modelAndView = null;
        model.addAttribute("modelSpecieModel", new ModelSpecieDTO());
        Page<ModelSpecification> list = modelSpecService.findAllOrderByName(PageRequest.of(page, 5, Sort.by(id)), search);
        modelAndView = new ModelAndView("admin/listModelSpec");
        if (!list.isEmpty()) {
            modelAndView.addObject("modelSpecs", list);
        } else {
            modelAndView.addObject("lst_empty", "List Empty!");
        }
        return modelAndView;
    }


    @PostMapping("/admin/modelSpec/create")
    public String create(@ModelAttribute(value = "modelSpecieModel") ModelSpecieDTO modelSpecieDTO,
                         RedirectAttributes ra) {
        try {
            String nameType = modelSpecieDTO.getNameType();
            String regex = "[a-zA-Z]+";
            if (!nameType.matches(regex)) {
                ra.addFlashAttribute("fail", "Name of Type Model Car not Null or is Number");
                return "redirect:/admin/modelSpec";
            }
            int number = modelSpecieDTO.getSeatNumber();
            if (number < 2 || number > 34) {
                ra.addFlashAttribute("fail", "Number Seat in range 2 -> 34");
                return "redirect:/admin/modelSpec";
            }
            ModelSpecification modelSpec = new ModelSpecification();
            modelSpec.setNameType(nameType);
            Validate validateName = new Validate();
            if (validateName.checkDuplicateModelSpec(modelSpecieDTO.getNameType(), modelSpecieDTO.getSeatNumber(), modelSpecService.getAllModelSpecification())) {
                ra.addFlashAttribute("fail", "ModelSpecification exist");
                return "redirect:/admin/modelSpec";
            }
            modelSpec.setSeatNumber(modelSpecieDTO.getSeatNumber());
            modelSpec.setStatus(1);
            modelSpecService.saveModelSpecification(modelSpec);
            ra.addFlashAttribute("success", "The ModelSpecification has been saved successfully");
            return "redirect:/admin/modelSpec";
        } catch (Exception e) {
            ra.addFlashAttribute("fail", "Add ModelSpecification Failed");
            return "redirect:/admin/modelSpec";
        }
    }

    @GetMapping("/admin/modelSpec/delete/{id}")
    public String deleteModelSpec(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
        try {
            modelSpecService.delete(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "admin/page404";
        }
        ra.addFlashAttribute("success", "The ModelSpecification has been deleted successfully");
        return "redirect:/admin/modelSpec";
    }


    @PostMapping("/admin/modelSpec/edit")
    public String update(@RequestParam Map<String, String> requestMap,
                         RedirectAttributes ra) {
        Long id = Long.parseLong(requestMap.get("id"));
        String name = requestMap.get("nameType");
        if (!requestMap.get("seatNumber").equals("")) {
            int seatNumber = Integer.parseInt(requestMap.get("seatNumber"));
            ModelSpecification modelSpecId = modelSpecService.findById(id);
            try {
                String regex = "[a-zA-Z]+";
                if (!name.matches(regex)) {
                    ra.addFlashAttribute("fail", "Name of car brand not Null or is Number");
                    return "redirect:/admin/modelSpec";
                }
                if (seatNumber < 2 || seatNumber > 34) {
                    ra.addFlashAttribute("fail", "Number Seat in range 2 -> 34");
                    return "redirect:/admin/modelSpec";
                }
                if (!name.toLowerCase().equals(modelSpecId.getNameType().toLowerCase())) {
                    Validate validateName = new Validate();
                    if (validateName.checkDuplicateModelSpec(name, seatNumber, modelSpecService.getAllModelSpecification())) {
                        ra.addFlashAttribute("fail", "ModelSpecification exist");
                        return "redirect:/admin/modelSpec";
                    }
                }
                modelSpecService.updateModelSpec(name, seatNumber, id);
                ra.addFlashAttribute("success", "The ModelSpecification has been saved successfully");
                return "redirect:/admin/modelSpec";
            } catch (Exception e) {
                ra.addFlashAttribute("fail", "Update ModelSpecification Failed");
                return "redirect:/admin/modelSpec";
            }
        }
        ra.addFlashAttribute("fail", "Number Seat in range 2 -> 34");
        return "redirect:/admin/modelSpec";
    }
}
