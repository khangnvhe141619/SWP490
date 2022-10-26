package com.workshop.carautionsystem.security.userPrinciple;

import com.workshop.carautionsystem.model.User;
import com.workshop.carautionsystem.repository.UserResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserResponsitory userResponsitory;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userResponsitory.findUserByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User not found") );
        return UserPrinciple.build(user);
    }
}
