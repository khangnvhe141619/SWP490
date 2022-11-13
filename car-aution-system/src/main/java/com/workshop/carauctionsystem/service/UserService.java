package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.entity.VerificationToken;
import com.workshop.carauctionsystem.model.UserDTO;

public interface UserService {
    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    public User registerNewUser(UserDTO userDTO);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);

    VerificationToken generateNewVerificationToken(String token);
}
