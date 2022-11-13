package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.entity.VerificationToken;
import com.workshop.carauctionsystem.model.UserDTO;
import com.workshop.carauctionsystem.repository.RoleRepository;
import com.workshop.carauctionsystem.repository.UserResponsitory;
import com.workshop.carauctionsystem.repository.VerificationTokenRepository;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserResponsitory userResponsitory;

    @Override
    public Optional<User> login(String username, String password) {
        return userResponsitory.findUserByUserNameAndPassword(username, password);
    }

    @Override
    public Optional<User> findUserByName(String username) {
        return userResponsitory.findUserByUserName(username);
    }

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
    public User getUser(String verificationToken) {
        final VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    @Override
    public void saveRegisteredUser(User user) {
        userResponsitory.save(user);
    }

    @Override
    public User registerNewUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("USER_ROLE")));
        return userResponsitory.save(user);
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
