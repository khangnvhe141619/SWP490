package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.PasswordResetToken;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.entity.VerificationToken;
import com.workshop.carauctionsystem.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User login (String username);
    public User findByUsername (String username);
    public User findByEmail(String email);
    public User findUserById (int id);
    public User checkPassword(int id, String pass);
    public void changePassword(int id, String newPass);
    void updateUserById(String fullName, String userName, String phone, String email, int id);
    User getUser(String verificationToken);
    void saveRegisteredUser(User user);
    public User registerNewUserAccount(UserDTO userDTO);
    void saveVerificationTokenForUser(User user, String token);
    VerificationToken getVerificationToken(String verificationToken);
    VerificationToken generateNewVerificationToken(String token);
    void savePasswordResetTokenForUser(User user, String token);
    PasswordResetToken getPasswordResetToken(String token);
    User getUserByPasswordResetToken(String token);
    void changePassword(User user, String password);
}
