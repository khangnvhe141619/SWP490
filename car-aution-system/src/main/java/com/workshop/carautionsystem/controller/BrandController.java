package com.workshop.carautionsystem.controller;


import com.workshop.carautionsystem.entity.Brand;
import com.workshop.carautionsystem.exception.NotFoundException;
import com.workshop.carautionsystem.service.BrandService;
import com.workshop.carautionsystem.service.impl.BrandServiceImpl;
import com.workshop.carautionsystem.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

@Controller
public class BrandController {

    @Autowired
    BrandServiceImpl brandService;

    @Autowired
    Validate validate;

    @GetMapping("/brand")
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String id) {
        ModelAndView modelAndView = null;
        Page<Brand> list = brandService.findAllOrderById(PageRequest.of(page, 5, Sort.by(id)));
        if (!list.isEmpty()) {
            modelAndView = new ModelAndView("index");
            modelAndView.addObject("brands", list);
        } else {
            modelAndView = new ModelAndView("page404");
        }
        return modelAndView;
    }

    @GetMapping("/brand/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = null;
        try {
            modelAndView = new ModelAndView("test");
            modelAndView.addObject("brand", new Brand());
            return modelAndView;
        } catch (Exception e) {
            modelAndView = new ModelAndView("page404");
            return modelAndView;
        }
    }

    @PostMapping("brand/create")
    public String create(@Valid @ModelAttribute(value = "brand") Brand brand,
                         BindingResult bindingResult, @RequestParam MultipartFile upImg,
                         RedirectAttributes ra, Model model) {
        validate.validate(brand, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "test";
        }
        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\DoAn\\SWP490\\car-aution-system\\src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            brand.setImgPath("/hoang/" + nameFile);
            brandService.saveBrand(brand);
            ra.addFlashAttribute("message", "The brand has been saved successfully");
            return "redirect:/brand";
        } catch (IOException e) {
            model.addAttribute("message", "Must upload a image");
            return "test";
        }
    }


    @GetMapping("/brand/delete/{id}")
    public String deleteBrand(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
        try {
            brandService.delete(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/brand";
    }

    @GetMapping("/brand/edit/{id}")
    public String showFormUpdate(@PathVariable(value = "id") Long id, Model model, RedirectAttributes ra) throws NotFoundException {
        Brand brand = brandService.findById(id);
        if (brand != null) {
            model.addAttribute("brand", brand);
            return "update";
        } else {
            throw new NotFoundException("Could not find any with ID");
        }
    }

    @PostMapping("brand/edit")
    public String update(@Valid @ModelAttribute(value = "brand") Brand brand,
                         BindingResult bindingResult, @RequestParam MultipartFile upImg, @RequestParam Long id,
                         RedirectAttributes ra) {
        String nameFile = upImg.getOriginalFilename();
        Brand brandId = brandService.findById(id);
        brand.setImgPath(brandId.getImgPath());
        try {
            if(!brand.getBrandName().equals(brandId.getBrandName())){
                validate.validate(brand, bindingResult);
                if (bindingResult.hasFieldErrors()) {
                    return "update";
                }
            }

            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\DoAn\\SWP490\\car-aution-system\\src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            brand.setImgPath("/hoang/" + nameFile);
            brandService.saveBrand(brand);
        } catch (IOException e) {
            brand.setImgPath(brandId.getImgPath());
            brandService.saveBrand(brand);
        }
        ra.addFlashAttribute("message", "The brand has been saved successfully");
        return "redirect:/brand";
    }

}
