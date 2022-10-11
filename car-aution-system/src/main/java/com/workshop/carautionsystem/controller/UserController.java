package com.workshop.carautionsystem.controller;

import com.workshop.carautionsystem.model.ResponseObject;
import com.workshop.carautionsystem.model.User;
import com.workshop.carautionsystem.repository.UserResponsitory;
import com.workshop.carautionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/login")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    UserResponsitory userResponsitory;
    @GetMapping("")
    public List<User> getAll(){
        return service.listUser();
    }
    @PostMapping("")
    public ResponseEntity<ResponseObject> login(@RequestBody User user){
        Optional<User> u=  service.login(user.getUserName(),user.getPassword());
        if(u.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successfully","Login success"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Failed","Login Failed"));
        }
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser){
        List<User> findUserName= userResponsitory.findUserByUserName(newUser.getUserName().trim());
        if(findUserName.size()>0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("Already","register faile"));
        }
        User u =new User();
        u.setUserName(newUser.getUserName());
        u.setPassword(newUser.getPassword());
        u.setEmail(newUser.getEmail());
        u.setPhone(newUser.getPhone());
        u.setRoleId(1);
        service.register(u);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","add success"));
    }

}
