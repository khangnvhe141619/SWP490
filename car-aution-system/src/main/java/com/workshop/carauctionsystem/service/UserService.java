package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.entity.VerificationToken;
import com.workshop.carauctionsystem.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<User> login (String username, String password);

    Optional<User> findUserByName (String username);
    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    boolean isEmailExist(String email);

    boolean isUsernameExist(String username);

    public User registerNewUserAccount(UserDTO userDTO);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);

    VerificationToken generateNewVerificationToken(String token);
}
