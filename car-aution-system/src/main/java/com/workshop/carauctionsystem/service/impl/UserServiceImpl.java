package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.entity.VerificationToken;
import com.workshop.carauctionsystem.exception.UserAlreadyExistException;
import com.workshop.carauctionsystem.model.UserDTO;
import com.workshop.carauctionsystem.repository.RoleRepository;
import com.workshop.carauctionsystem.repository.UserRepository;
import com.workshop.carauctionsystem.repository.VerificationTokenRepository;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    private RoleRepository roleRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findUserByUserNameAndPassword(username, password);
    }

    @Override
    public User findUserByName(String username) {
        return userRepository.findUserByUserName(username);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void updateUserById(String fullName, String userName, String phone, String email, int id){
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
    @Override
    public boolean isEmailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public boolean isUsernameExist(String username) {
        return userRepository.findByUserName(username) != null;
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
    public void createVerificationTokenForUser(User user, String token) {
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
}
