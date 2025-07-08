package com.jwt_security.JwtSpringSecurity.controller;

import com.jwt_security.JwtSpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/health")
    public String health(){
        return "Every thing is healthy";
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUser(){
        return userService.getAllUser();
    }
}
