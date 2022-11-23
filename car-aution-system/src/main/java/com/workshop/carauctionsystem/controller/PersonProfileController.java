package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.repository.UserRepository;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Controller
public class PersonProfileController {

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
                                       @RequestParam(name = "newPassword", required = false) String newPass,
                                       @CookieValue(value = "setUserId") int setUserId, Model model){
        User u =  service.findUserById(setUserId);
        boolean checkPass = BCrypt.checkpw(password, u.getPassword());
        if(u != null && checkPass){
            service.changePassword(setUserId, newPass);
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/personProfile");
        return view;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @PostMapping("/passExist")
    public ResponseEntity<ResponseObject> isPassExisted(@CookieValue(value = "setUserId") int setUserId,
                                                        @RequestParam("currentPassword") String pass) {
        User u =  service.findUserById(setUserId);
        boolean checkPass = BCrypt.checkpw(pass, u.getPassword());
        if (u != null && checkPass) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Valid password!", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Invalid password!", null));
    }
}
