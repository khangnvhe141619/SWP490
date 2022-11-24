package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.PasswordResetToken;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.entity.VerificationToken;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.exception.UserAlreadyExistException;
import com.workshop.carauctionsystem.model.UserDTO;
import com.workshop.carauctionsystem.repository.PasswordResetTokenRepository;
import com.workshop.carauctionsystem.repository.RoleRepository;
import com.workshop.carauctionsystem.repository.UserRepository;
import com.workshop.carauctionsystem.repository.VerificationTokenRepository;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordResetTokenRepository passwordTokenRepo;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User login(String username) {
        return userRepository.findUserByUserName(username);
    }
    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUserName(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void changePassword(int id, String newPass) {
        userRepository.changePasswordById(id, passwordEncoder.encode(newPass));
    }

    @Override
    public void updateUserById(String fullName, String userName, String phone, String email, int id) {
        userRepository.updateUserById(fullName, userName, phone, email, id);
    }

    @Override
    public User getUser(String verificationToken) {
        final VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    public boolean isEmailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User registerNewUserAccount(UserDTO userDTO) {
        if (isEmailExist(userDTO.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDTO.getEmail());
        }
        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setAvatar(userDTO.getAvatar());
        user.setRoles(Arrays.asList(roleRepository.findByName("USER_ROLE")));
        Date date = new Date();
        user.setCreatedAt(new Timestamp(date.getTime()));
        user.setAddressWallet(userDTO.getAddressWallet());
        return userRepository.save(user);
    }

    @Override
    public void saveVerificationTokenForUser(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }

    @Override
    public VerificationToken generateNewVerificationToken(String token) {
        VerificationToken vToken = verificationTokenRepository.findByToken(token);
        vToken.updateToken(UUID.randomUUID()
                .toString());
        vToken = verificationTokenRepository.save(vToken);
        return vToken;
    }

    @Override
    public void savePasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepo.save(myToken);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordTokenRepo.findByToken(token);
    }

    @Override
    public User getUserByPasswordResetToken(String token) {
        return passwordTokenRepo.findByToken(token).getUser();
    }

    @Override
    public void changePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public Page<User> ListUserBan(Pageable pageable, String name) {
        if (name != null) {
            return userRepository.ListUserBan(pageable, name);
        }else {
            return userRepository.ListUserBan(pageable);
        }
    }

    @Override
    public Page<User> ListUserUnBan(Pageable pageable, String name) {
        if (name != null) {
            return userRepository.ListUserUnban(pageable, name);
        }else {
            return userRepository.ListUserUnban(pageable);
        }
    }

    // admin

    @Override
    public void UnBanUser(Long id) throws NotFoundException {
        userRepository.UnBanUser(id);
    }

    @Override
    public void BanUser(Long id) throws NotFoundException{
        userRepository.BanUser(id);
    }

    @Override
    public List<User> getRoleByAdminCar() {
        return userRepository.getRoleByAdminCar();
    }

    @Override
    public List<User> getRoleByAdminAuction() {
        return userRepository.getRoleByAdminAuction();
    }
}
