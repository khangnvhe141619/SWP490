package com.workshop.carautionsystem.controller;


import com.workshop.carautionsystem.entity.Brand;
import com.workshop.carautionsystem.exception.NotFoundException;
import com.workshop.carautionsystem.service.BrandService;
import com.workshop.carautionsystem.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class BrandController {

    @Autowired
    BrandService brandService;

    @Autowired
    Validate validateBrand;

    //display list brand by page
    @GetMapping("/brand")
    public ModelAndView showListBrand(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String id) throws IOException {
        List<Brand> brands = brandService.getAllBrand();
        ModelAndView modelAndView = null;
        if (!brands.isEmpty()) {
            modelAndView = new ModelAndView("index");
            modelAndView.addObject("brands", brandService.findAllById(PageRequest.of(page, 5, Sort.by(id))));
        } else {
            modelAndView = new ModelAndView("page404");
            modelAndView.addObject("error");
        }
        return modelAndView;
    }

    @GetMapping("/brand/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("brand", new Brand());
        return modelAndView;
    }

    @PostMapping("/brand/create")
    public String create(@Valid @ModelAttribute("brand") Brand brand, BindingResult bindingResult, @RequestParam MultipartFile upImg, RedirectAttributes ra) {

        validateBrand.validate(brand, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "test";
        }
        String fileName = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(),
                    new File("/E:\\DoAn\\SWP490\\car-aution-system\\src\\main\\resources\\templates\\hoang/" + fileName));
            brand.setImgPath("/hoang/" + fileName);
            brandService.saveBrand(brand);
        } catch (IOException e) {
            brand.setImgPath("/hoang/" + fileName);
            brandService.saveBrand(brand);
            e.getStackTrace();
        }
        ra.addFlashAttribute("message", "The brand has saved successfully");
        return "redirect:/brand";
    }

    @GetMapping("/brand/edit/{id}")
    public String showFromEdit(@PathVariable(value = "id") long id, Model model, RedirectAttributes ra) {
        try {
            Brand brand = brandService.findById(id);
            model.addAttribute("brand", brand);
            return "update";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/brand";
        }
    }

    @GetMapping("/brand/delete/{id}")
    public String deleteBrand(@PathVariable(value = "id") long id, RedirectAttributes ra) {
        try {
            brandService.delete(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/brand";
    }

}
