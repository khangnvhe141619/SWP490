package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.entity.User;
import com.workshop.carautionsystem.entity.VerificationToken;
import com.workshop.carautionsystem.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<User> login (String username, String password);

    Optional<User> findUserByName (String username);
    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    public User registerNewUser(UserDTO userDTO);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);

    VerificationToken generateNewVerificationToken(String token);
}
