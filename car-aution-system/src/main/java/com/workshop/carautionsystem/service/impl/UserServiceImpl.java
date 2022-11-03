package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.model.User;
import com.workshop.carautionsystem.repository.UserResponsitory;
import com.workshop.carautionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserResponsitory responsitory;
    @Override
    public Optional<User> login(String username,String password) {
        return responsitory.findUserByUserNameAndPassword(username,password);
    }

    @Override
    public List<User> listUser() {
        return responsitory.findAll();
    }

    @Override
    public User register(User user) {
        return responsitory.save(user);
    }

    @Override
    public User viewProfile(int id) {
        return responsitory.findUserById(id);
    }
}
