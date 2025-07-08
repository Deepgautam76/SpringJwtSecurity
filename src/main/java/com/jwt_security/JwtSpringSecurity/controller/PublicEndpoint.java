package com.jwt_security.JwtSpringSecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicEndpoint {
    @GetMapping
    public ResponseEntity<Void> redirectToSwagger() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/swagger-ui/index.html"); // or /swagger-ui.html
        return new ResponseEntity<>(headers, HttpStatus.FOUND); // 302 Redirect
    }
    @GetMapping("/home")
    public ResponseEntity<?> home(){
        return new ResponseEntity<>("Hello from security home",HttpStatus.OK);
    }
}
