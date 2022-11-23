package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.PasswordResetToken;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.repository.PasswordResetTokenRepository;
import com.workshop.carauctionsystem.repository.UserRepository;
import com.workshop.carauctionsystem.service.UserDetailsService;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

@Controller
public class ResetPasswordController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordResetTokenRepository passTokenRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Environment env;

    @GetMapping("/forgotPassword")
    public String showRegistrationForm() {
        return "Sign-In-Up";
    }
    @PostMapping("/forgotPassword")
    public ModelAndView resetPassword(HttpServletRequest request, @RequestParam("email") String email) {
        User user = userRepository.findByEmail(email);
        ModelAndView view = new ModelAndView();
        if (user == null) {
            view.addObject("message_regSuccess", "Send link successfully!");
            view.setViewName("successRegister");
            return view;
        }

        String token = UUID.randomUUID().toString();
        userService.savePasswordResetTokenForUser(user, token);
        try {
            String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            SimpleMailMessage emailMess = constructResetTokenEmail(appUrl, token, user);
            mailSender.send(emailMess);
        } catch (Exception e) {
            view.setViewName("page404");
            return view;
        }
        view.addObject("message_regSuccess", "Send link successfully!");
        view.setViewName("successRegister");
        return view;
    }

    @GetMapping("/user/changePassword")
    public ModelAndView changePassword(@RequestParam("id") int id, @RequestParam("token") String token) {
        ModelAndView view = new ModelAndView();
        PasswordResetToken passToken = userService.getPasswordResetToken(token);
        if (passToken == null || passToken.getUser().getId() != id) {
            view.addObject("message_regSuccess", "Invalid token!");
            view.setViewName("successRegister");
            return view;
        }

        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            view.addObject("message_regSuccess", "Token expired!");
            view.setViewName("successRegister");
            return view;
        }

        view.addObject("param_token", token);
        view.setViewName("resetPassword");
        return view;
    }

    @PostMapping("/user/savePassword")
    public ModelAndView savePassword(@RequestParam("token") String token, @RequestParam("password") String password) {
        ModelAndView view = new ModelAndView();
        User user = userService.getUserByPasswordResetToken(token);
        userService.changePassword(user, password);
        view.addObject("message_regSuccess", "Change successfully!");
        view.setViewName("successRegister");
        return view;
    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final String token, final User user) {
        final String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Reset Password");
        email.setText("Reset Password" + " \r\n" + url);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }
}
