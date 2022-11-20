package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.entity.VerificationToken;
import com.workshop.carauctionsystem.exception.UserAlreadyExistException;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.model.UserDTO;
import com.workshop.carauctionsystem.registration.OnRegistrationCompleteEvent;
import com.workshop.carauctionsystem.repository.UserRepository;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Random;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private Environment env;
    @Autowired
    private JavaMailSender mailSender;


    public RegistrationController() {
        super();
    }

    @ModelAttribute("userDTO")
    public UserDTO setUserDTO() {
        return new UserDTO();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(final HttpServletRequest request) {
        return "Sign-in-Up";
    }

    @GetMapping("/404")
    public String show404(final HttpServletRequest request) {
        return "page404";
    }

    @GetMapping("/successRegister")
    public String showSuccessRegister(final HttpServletRequest request) {
        return "successRegister";
    }

    @PostMapping("/registration")
    public ModelAndView registerAccount(@ModelAttribute(name = "setUserDTO") UserDTO userDTO, HttpServletRequest request) {
        Random generator = new Random();
        ModelAndView mav = new ModelAndView();
        try {
            userDTO.setAvatar("img/profile/user.png");
            userDTO.setPhone("099999999");
            userDTO.setFullName("User" + generator.nextInt(100));
            userDTO.setAddressWallet("empty");
            User registeredUser = userService.registerNewUserAccount(userDTO);

            String appUrl = getAppUrl(request);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, appUrl));
        } catch (UserAlreadyExistException ux) {
            mav = new ModelAndView("Sign-In-Up", "userDTO", userDTO);
            String errMessage = "";
            mav.addObject("message", errMessage);
            return mav;
        } catch (RuntimeException rx) {
            return new ModelAndView(show404(request), "userDTO", userDTO);
        }
        mav.addObject("message_regSuccess", "Register Successfully!");
        mav.setViewName("successRegister");
        return mav;
    }

    @GetMapping("/registrationConfirm")
    public ModelAndView confirmRegistration(HttpServletRequest request, Model model, @RequestParam("token") String token) {
        VerificationToken verificationToken = userService.getVerificationToken(token);
        ModelAndView view = new ModelAndView();
        if (verificationToken == null) {
            view.addObject("message_regSuccess", token + " doesn't exist!");
            view.setViewName("successRegister");
            return view;
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message_regSuccess", token + " expired!");
            view.setViewName("successRegister");
            return view;
        }
        user.setEnabled(1);
        userService.saveRegisteredUser(user);
        model.addAttribute("message_regSuccess", "Account Verified");
        view.setViewName("successRegister");
        return view;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @PostMapping("/emailExist")
    public ResponseEntity<ResponseObject> isEmailExisted(HttpServletRequest request, @RequestParam("email") String email) {
        boolean _email = userService.isEmailExist(email);
        if (_email) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Email existed!", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Valid email!", null));
    }

    @PostMapping("/usernameExist")
    public ResponseEntity<ResponseObject> isUsernameExist(HttpServletRequest request, @RequestParam("username") String username) {
        boolean _username = userService.isUsernameExist(username);
        if (_username) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Username existed!", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Valid username!", null));
    }
}
