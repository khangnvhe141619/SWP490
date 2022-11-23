package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminListUserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/admin/user")
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "id") String id,
                                 @RequestParam(defaultValue = "") String search,
                                 Model model) {
        ModelAndView modelAndView = null;
        Page<User> list = userService.ListUserUnBan(PageRequest.of(page, 5, Sort.by(id)), search);
        if (!list.isEmpty()) {
            modelAndView = new ModelAndView("admin/listUser");
            modelAndView.addObject("users", list);
        } else {
            modelAndView = new ModelAndView("page404");
        }
        return modelAndView;
    }

    @GetMapping("/admin/user/ban/{id}")
    public String deleteBrand(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
        try {
            userService.BanUser(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "page404";
        }
        ra.addFlashAttribute("success", "Ban User successfully");
        return "redirect:/admin/user";
    }

}
