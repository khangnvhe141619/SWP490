package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.repository.UserResponsitory;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.transaction.Transactional;
import java.util.List;

@Controller
public class personProfileController {

    @Autowired
    UserService service;

    @Autowired
    UserResponsitory userResponsitory;

    @GetMapping(value = {"/personProfile"})
    public String getPersonProfile( User user, @CookieValue(value = "setUserId") int setUserId, Model model){
        User u=  service.findUserById(setUserId);
        if(u != null){
            String name = u.getFullName();
            String email = u.getEmail();
            String phone = u.getPhone();
            String username = u.getUserName();
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("phone", phone);
            model.addAttribute("username", username);
        }
        return "PeronalProfile";
    }

    @Transactional
    @PostMapping(value = "/personProfile/update")
    public ModelAndView updatePerson(@ModelAttribute(name = "setUser") User user, @CookieValue(value = "setUserId") int setUser){
        String userName = user.getUserName();
        String fullName = user.getFullName();
        String phone = user.getPhone();
        String email = user.getEmail();
        service.updateUserById(fullName, userName, phone, email, setUser);
        System.out.println(userName + fullName + phone + email);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/personProfile");
        return view;
    }
}
