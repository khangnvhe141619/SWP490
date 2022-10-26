package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.model.User;
import com.workshop.carautionsystem.model.UserDTO;
import com.workshop.carautionsystem.model.VerificationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    public Optional<User> login(String username, String password);

    public List<User> listUser();

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    public User registerNewUser(UserDTO userDTO);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);


}
