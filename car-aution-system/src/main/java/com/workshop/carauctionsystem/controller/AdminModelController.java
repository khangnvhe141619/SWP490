package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.entity.ModelSpecification;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.model.ModelCarDTO;
import com.workshop.carauctionsystem.service.impl.BrandServiceImpl;
import com.workshop.carauctionsystem.service.impl.ModelServiceImpl;
import com.workshop.carauctionsystem.service.impl.ModelSpecificationServiceImpl;
import com.workshop.carauctionsystem.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.Pattern;
import java.util.Map;

@Controller
public class AdminModelController {
    @Autowired
    private ModelServiceImpl modelService;

    @Autowired
    private BrandServiceImpl brandService;

    @Autowired
    private ModelSpecificationServiceImpl modelSpecService;

    @GetMapping("/admin/model")
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "id") String id,
                                 @RequestParam(defaultValue = "") String search,
                                 Model model) {
        ModelAndView modelAndView = null;
        model.addAttribute("modelCar", new ModelCarDTO());
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("modelType", modelSpecService.getAllModelSpecification());
        Page<ModelCar> list = modelService.findAllOrderByName(PageRequest.of(page, 5, Sort.by(id)), search);
        modelAndView = new ModelAndView("admin/listModel");
        if (!list.isEmpty()) {
            modelAndView.addObject("models", list);
        } else {
            modelAndView.addObject("lst_empty", "List Empty!");
        }
        return modelAndView;
    }

    @PostMapping("/admin/model/create")
    public String create(@ModelAttribute(value = "modelCar") ModelCarDTO modelCarDTO,
                         @RequestParam Map<String, String> requestMap,
                         RedirectAttributes ra) {

        if(requestMap.get("idModelSpec") != null && requestMap.get("idBrand") != null) {
            try {
                ModelSpecification modelSpec = new ModelSpecification();
                modelSpec.setId(Long.parseLong(requestMap.get("idModelSpec")));
                Brand brand = new Brand();
                brand.setId(Long.parseLong(requestMap.get("idBrand")));

                ModelCar modelCar = new ModelCar();
                modelCar.setModelSpecificationId(modelSpec);
                modelCar.setBrandId(brand);
                modelCar.setStatus(1);
                modelCar.setModelName(modelCarDTO.getModelName());
                Validate validateExit = new Validate();
                String modelName = modelCarDTO.getModelName();
                Long brandId = Long.parseLong(requestMap.get("idBrand"));

                String regex = "[a-zA-Z0-9]+";
                if (!modelName.matches(regex)){
                    ra.addFlashAttribute("fail", "Add New Car Model Failed (Don't enter special characters)");
                    return "redirect:/admin/model";
                }
                if (validateExit.checkDuplicateModel(modelName, brandId, modelService.getAllModelByStatus())) {
                    ra.addFlashAttribute("fail", "This Car Model already exists");
                    return "redirect:/admin/model";
                }

                modelService.saveModel(modelCar);
                ra.addFlashAttribute("success", "The Car Model has been saved successfully");
                return "redirect:/admin/model";
            } catch (Exception e) {
                ra.addFlashAttribute("fail", "Add New Car Model Failed");
                return "redirect:/admin/model";
            }
        }else{   ra.addFlashAttribute("fail", "Add New Car Model Failed");
            return "redirect:/admin/model";
        }
    }


    @GetMapping("/admin/model/delete/{id}")
    public String deleteModel(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
        try {
            modelService.delete(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "admin/page404";
        }
        ra.addFlashAttribute("success", "The Model Car has been deleted successfully");
        return "redirect:/admin/model";
    }

    @PostMapping("/admin/model/edit")
    public String update(@RequestParam Map<String, String> requestMap,
                         RedirectAttributes ra) {

        Long id = Long.parseLong(requestMap.get("id"));
        Long idModelSpec = Long.parseLong(requestMap.get("idModelSpec"));
        Long idBrand = Long.parseLong(requestMap.get("idBrand"));
        String modelName = requestMap.get("modelName");
        ModelCar modelId = modelService.findById(id);

        try {
            String regex = "[a-zA-Z0-9]+";
            if (!modelName.matches(regex)){
                ra.addFlashAttribute("fail", "Add New Car Model Failed (Don't enter special characters)");
                return "redirect:/admin/model";
            }
            Validate validateName = new Validate();
            if (!modelName.toLowerCase().equals(modelId.getModelName().toLowerCase())) {
                if (validateName.checkDuplicateModel(modelName, idBrand, modelService.getAllModelByStatus())) {
                    ra.addFlashAttribute("fail", "Model exist");
                    return "redirect:/admin/model";
                }
            }
            modelService.updateModel(idBrand, modelName, idModelSpec, id);
            ra.addFlashAttribute("success", "The Car Model has been saved successfully");
            return "redirect:/admin/model";
        } catch (Exception e) {
            ra.addFlashAttribute("fail", "Update New Car Model Failed");
            return "redirect:/admin/model";
        }
    }
}
