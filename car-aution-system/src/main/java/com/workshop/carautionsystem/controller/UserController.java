package com.workshop.carautionsystem.controller;

import com.workshop.carautionsystem.model.ResponseObject;
import com.workshop.carautionsystem.model.Role;
import com.workshop.carautionsystem.model.User;
import com.workshop.carautionsystem.model.UserDTO;
import com.workshop.carautionsystem.model.VerificationToken;
import com.workshop.carautionsystem.registration.OnRegistrationCompleteEvent;
import com.workshop.carautionsystem.repository.UserResponsitory;
import com.workshop.carautionsystem.security.jwt.JwtProvider;
import com.workshop.carautionsystem.security.jwt.JwtResponse;
import com.workshop.carautionsystem.security.userPrinciple.UserPrinciple;
import com.workshop.carautionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserResponsitory userResponsitory;
    @Autowired

    private JavaMailSender mailSender;
    @Autowired
    private Environment env;
    @Autowired
    private MessageSource messages;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("")
    public List<User> getAllUser() {
        return userService.listUser();
	}
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @GetMapping("")
    public List<User> getAll(){
        return userService.listUser();
    }
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getUserName(), userPrinciple.getAuthorities()));
    }
//    @PostMapping("/register")
//    public ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser){
//        Optional<User> findUserName= userResponsitory.findUserByUserName(newUser.getUserName().trim());
//        if(findUserName==null){
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("Already","register faile"));
//        }
//        User u =new User();
//        u.setUserName(newUser.getUserName());
//        u.setPassword(passwordEncoder.encode(newUser.getPassword()));
//        u.setEmail(newUser.getEmail());
//        u.setPhone(newUser.getPhone());
//
//        userService.registerNewUser(u);
//
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","add success"));
//    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login2(@RequestBody User user) {
        Optional<User> u = userService.login(user.getUserName(), user.getPassword());
        if (u.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successfully", "Login success"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Failed", "Login Failed"));
        }
    }

    // Registration
    @PostMapping("/user/registration")
    public ResponseEntity<ResponseObject> registerUserAccount(@RequestBody UserDTO userDTO, final HttpServletRequest request) {
        System.out.println(userDTO);
        User user = userService.registerNewUser(userDTO);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, getAppUrl(request)));
        System.out.println(getAppUrl(request));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "sign successfully"));
    }

    // resend verification
    @GetMapping("/user/resendRegistrationToken")
    public ResponseEntity<ResponseObject> resendRegistrationToken(final HttpServletRequest request, @RequestParam("token") final String existingToken) {
        final VerificationToken newToken = userService.generateNewVerificationToken(existingToken);
        final User user = userService.getUser(newToken.getToken());
        mailSender.send(constructResendVerificationTokenEmail(getAppUrl(request), request.getLocale(), newToken, user));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "re-send successfully"));
    }

    //verification
    @GetMapping("/registrationConfirm")
    public String confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) {

        final VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "not exist")).toString();
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "expired")).toString();

        }
        user.setEnabled(1);
        userService.saveRegisteredUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "actived successfully")).toString();
    }

    // ============== NON-API ============

    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final VerificationToken newToken, final User user) {
        final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = messages.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }
}
