package com.workshop.carauctionsystem.controller;


import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.model.BrandModel;
import com.workshop.carauctionsystem.service.impl.BrandServiceImpl;
import com.workshop.carauctionsystem.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
public class AdminBrandController {

    @Autowired
    BrandServiceImpl brandService;

    @GetMapping("/brand")
    //get list brand by page
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "id") String id,
                                 @RequestParam(defaultValue = "") String search,
                                 Model model) {
        ModelAndView modelAndView = null;
        //get list brand by page and sort by id desc
        model.addAttribute("brandModel", new BrandModel());
        Page<Brand> list = brandService.findAllOrderByName(PageRequest.of(page, 5, Sort.by(id)), search);
        if (!list.isEmpty()) {
            modelAndView = new ModelAndView("admin/listBrand");
            modelAndView.addObject("brands", list);
        } else {
            modelAndView = new ModelAndView("page404");
        }
        return modelAndView;
    }

    @PostMapping("brand/create")
    public String create(@ModelAttribute(value = "brandModel") BrandModel brandModel,
                         @RequestParam MultipartFile upImg, RedirectAttributes ra) {
        try {
            String nameFile = upImg.getOriginalFilename();
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\DoAn\\SWP490\\car-aution-system\\src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            Brand brand = new Brand();
            brand.setBrandName(brandModel.getName());
            Validate validateName = new Validate();
            if (validateName.checkDuplicateBrand(brandModel.getName(), brandService.getAllBrand())) {
                ra.addFlashAttribute("fail", "This car brand already exists");
                return "redirect:/brand";
            }
            brand.setStatus(Long.valueOf(1));
            brand.setImgPath("/hoang/" + nameFile);
            brandService.saveBrand(brand);
            ra.addFlashAttribute("success", "The brand has been saved successfully");
            return "redirect:/brand";
        } catch (IOException e) {
            ra.addFlashAttribute("fail", "Must upload a image");
            return "redirect:/brand";
        }
    }


    @GetMapping("/brand/delete/{id}")
    public String deleteBrand(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
        try {
            brandService.delete(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "page404";
        }
        ra.addFlashAttribute("success", "The brand has been deleted successfully");
        return "redirect:/brand";
    }


    @PostMapping("brand/edit")
    public String update(@RequestParam MultipartFile upImg, RedirectAttributes ra,
                         @RequestParam Map<String, String> requestMap) {

        Long id = Long.parseLong(requestMap.get("id"));
        String name = requestMap.get("name");
        String nameFile = upImg.getOriginalFilename();
        Brand brandId = brandService.findById(id);
        try {
            if (!name.toLowerCase().equals(brandId.getBrandName().toLowerCase())) {
                Validate validateName = new Validate();
                if (validateName.checkDuplicateBrand(name, brandService.getAllBrand())) {
                    ra.addFlashAttribute("fail", "This car brand already exists");
                    return "redirect:/brand";
                }
            }
            //brandId.setBrandName(name);
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\DoAn\\SWP490\\car-aution-system\\src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            String img = "/hoang/" + nameFile;
            brandService.updateBrand(name, img, id);
        } catch (IOException e) {
            //  brandId.setImgPath(brandId.getImgPath());
            String img = brandId.getImgPath();
            brandService.updateBrand(name, img, id);
        }
        ra.addFlashAttribute("success", "The brand has been saved successfully");
        return "redirect:/brand";
    }
}
