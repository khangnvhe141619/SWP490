package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    public Optional<User> login (String username,String password);
    public List<User> listUser();
    public User register(User user);
    public User viewProfile(int id);
}
