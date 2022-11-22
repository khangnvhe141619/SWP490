package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.repository.UserRepository;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Controller
public class personProfileController {

    @Autowired
    UserService service;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServletContext application;

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
            model.addAttribute("INFOR", u);
        }
        return "PeronalProfile";
    }

    @PostMapping(value = "/personProfile/update")
    public ModelAndView updatePerson(@ModelAttribute(name = "setUser") User user,
                                     @CookieValue(value = "setUserId") int setUserId,
                                     @RequestParam("photo") MultipartFile photo, Model model){
        String userName = user.getUserName();
        String fullName = user.getFullName();
        String phone = user.getPhone();
        String email = user.getEmail();
        User u=  service.findUserById(setUserId);
        Path path = Paths.get("src/main/resources/static/assets/img/avatar");
        try {
            InputStream inputStream = photo.getInputStream();
            if(!photo.isEmpty()){
                Files.copy(inputStream, path.resolve(photo.getOriginalFilename()),
                        StandardCopyOption.REPLACE_EXISTING);
                u.setAvatar(photo.getOriginalFilename().toLowerCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        service.updateUserById(fullName, userName, phone, email, setUserId);
        model.addAttribute("INFOR", u);
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/personProfile");
        return view;
    }

    @PostMapping(value = "/personProfile/changePassword")
    public ModelAndView changePassword(@RequestParam(name = "currentPassword", required = false) String password,
                                       @RequestParam(name = "newPassword", required = false) String repass,
                                       @CookieValue(value = "setUserId") int setUser, Model model){
        User u = service.checkPassword(setUser, password);
        if(u != null){
            service.changePassword(setUser, repass);
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/personProfile");
        return view;
    }
}
