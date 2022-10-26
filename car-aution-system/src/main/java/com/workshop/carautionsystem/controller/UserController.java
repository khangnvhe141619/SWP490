package com.workshop.carautionsystem.controller;

import com.workshop.carautionsystem.model.ResponseObject;
import com.workshop.carautionsystem.model.Role;
import com.workshop.carautionsystem.model.User;
import com.workshop.carautionsystem.repository.UserResponsitory;
import com.workshop.carautionsystem.security.jwt.JwtProvider;
import com.workshop.carautionsystem.security.jwt.JwtResponse;
import com.workshop.carautionsystem.security.userPrinciple.UserPrinciple;
import com.workshop.carautionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/login")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    UserResponsitory userResponsitory;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @GetMapping("")
    public List<User> getAll(){
        return service.listUser();
    }
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getUserName(), userPrinciple.getAuthorities()));
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser){
        Optional<User> findUserName= userResponsitory.findUserByUserName(newUser.getUserName().trim());
        if(findUserName==null){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("Already","register faile"));
        }
        User u =new User();
        u.setUserName(newUser.getUserName());
        u.setPassword(passwordEncoder.encode(newUser.getPassword()));
        u.setEmail(newUser.getEmail());
        u.setPhone(newUser.getPhone());

        service.register(u);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","add success"));
    }

}
