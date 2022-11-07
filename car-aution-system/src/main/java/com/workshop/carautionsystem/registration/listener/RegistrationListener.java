//package com.workshop.carautionsystem.registration.listener;
//
//import com.workshop.carautionsystem.entity.User;
//import com.workshop.carautionsystem.registration.OnRegistrationCompleteEvent;
//import com.workshop.carautionsystem.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.MessageSource;
//import org.springframework.core.env.Environment;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//
//import java.util.Locale;
//import java.util.UUID;
//
//@Component
//public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private MessageSource messages;
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Autowired
//    private Environment env;
//
//    @Override
//    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
//        this.confirmRegistration(event);
//    }
//
//    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
//        final User user = event.getUser();
//        final String token = UUID.randomUUID().toString();
//        userService.createVerificationTokenForUser(user, token);
//
//        final SimpleMailMessage email = constructEmailMessage(event, user, token);
//        mailSender.send(email);
//    }
//    private SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) {
//        final String recipientAddress = user.getEmail();
//        final String subject = "Registration Confirmation";
//        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
//        final String message = messages.getMessage("message.regSuccLink", null, "You registered successfully. To confirm your registration, please click on the below link.", Locale.ENGLISH);
//        final SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + " \r\n" + confirmationUrl);
//        email.setFrom(env.getProperty("support.email"));
//        return email;
//    }
//}
