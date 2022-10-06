package com.workshop.carautionsystem.controller;

import com.workshop.carautionsystem.model.User;
import com.workshop.carautionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/login")
public class UserController {
    @Autowired
    UserService service;
    @GetMapping("")
    public List<User> getAll(){
        return service.listUser();
    }
    @PostMapping("")
    public Optional<User> login(@RequestBody User user){
        return Optional.ofNullable(service.login(user.getUserName(), user.getPassword()).orElseThrow(() -> new RuntimeException("faile")));
    }
}
