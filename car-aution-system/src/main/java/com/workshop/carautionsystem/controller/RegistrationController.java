package com.workshop.carautionsystem.controller;

import com.workshop.carautionsystem.model.UserDTO;
import com.workshop.carautionsystem.repository.UserResponsitory;
import com.workshop.carautionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @Autowired
    UserResponsitory userResponsitory;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private Environment env;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MessageSource messages;

    public RegistrationController() {
        super();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(final HttpServletRequest request, final Model model) {
        final UserDTO accountDTO = new UserDTO();
        model.addAttribute("user", accountDTO);
        return "registration";
    }



}
