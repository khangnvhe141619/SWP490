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

@RestController
@RequestMapping(path = "/api/v1/profile")

public class ProfileController {
    @Autowired
    UserResponsitory userResponsitory;

    @GetMapping("/view/{id}")
    public ResponseEntity<ResponseObject> viewProfile(@PathVariable int id){
        User viewProfile = userResponsitory.findUserById(id);
        if(viewProfile != null){
            String view = viewProfile.toString();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", view));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("NOT OK","Failed"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateProfile(@RequestBody User user, @PathVariable int id){
        User userById = userResponsitory.findUserById(id);
        try {
            if(userById != null){
                userById.setUserName(user.getUserName());
                userById.setEmail(user.getEmail());
                userById.setfName(user.getfName());
                userById.setlName(user.getlName());
                userById.setPhone(user.getPhone());
                userResponsitory.save(userById);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Update successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("NOT OK", "Id not already taken"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Update failed"));
        }
    }
}
