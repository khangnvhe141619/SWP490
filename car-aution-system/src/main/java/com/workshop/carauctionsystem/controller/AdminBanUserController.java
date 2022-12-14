package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.model.BrandModel;
import com.workshop.carauctionsystem.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminBanUserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/admin/userban")
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "id") String id,
                                 @RequestParam(defaultValue = "") String search,
                                 @CookieValue(value = "setUser", defaultValue = "") String setUser,
                                 Model model) {
        ModelAndView modelAndView = null;
        model.addAttribute("userName", setUser);
        Page<User> list = userService.ListUserBan(PageRequest.of(page, 5, Sort.by(id)), search);
        if (!list.isEmpty()) {
            modelAndView = new ModelAndView("admin/listBannedUser");
            modelAndView.addObject("users", list);
        } else {
            modelAndView = new ModelAndView("admin/listBannedUser");
            modelAndView.addObject("lst_empty", "List Empty!");
        }
        return modelAndView;
    }

    @GetMapping("/admin/userban/unban/{id}")
    public String deleteBrand(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
        try {
            userService.UnBanUser(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "admin/page404";
        }
        ra.addFlashAttribute("success", "Unban User successfully");
        return "redirect:/admin/userban";
    }
}
