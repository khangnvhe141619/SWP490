package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.PasswordResetToken;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.entity.VerificationToken;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.model.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User login (String username, String password);

    public User findByUsername (String username);
    public User findByEmail(String email);
    public User findUserById (int id);

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

    // admin load list and update ban and unban

    Page<User> ListUserBan(Pageable pageable,String name);
    Page<User> ListUserUnBan(Pageable pageable,String name);
    void UnBanUser(Long id) throws NotFoundException;
    void BanUser(Long id) throws NotFoundException;
}
