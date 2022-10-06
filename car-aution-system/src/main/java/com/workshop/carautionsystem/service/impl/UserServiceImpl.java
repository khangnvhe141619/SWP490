package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.model.User;
import com.workshop.carautionsystem.responsitory.UserResponsitory;
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
}