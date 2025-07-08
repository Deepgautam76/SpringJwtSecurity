package com.jwt_security.JwtSpringSecurity.service;

import com.jwt_security.JwtSpringSecurity.Dtos.Requests.AuthSignUpRequestDto;
import com.jwt_security.JwtSpringSecurity.Dtos.Responses.UserResponse;
import com.jwt_security.JwtSpringSecurity.model.User;
import com.jwt_security.JwtSpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public ResponseEntity<?> getAllUser() {
        List<User> users=userRepository.findAll();
        List<UserResponse> userResponses=new ArrayList<>();
        for(User user:users){
            userResponses.add(new UserResponse(user.getUsername(),user.getPassword()));
        }
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    public String registerUser(AuthSignUpRequestDto request) {
        User user=new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        if(user.getRole()==null){
         user.setRole("ROLE_STUDENT");
        }else{
            user.setRole("ROLE_"+user.getRole());
        }
        userRepository.save(user);
        return "Register success";
    }
    public boolean isExist(String username) {
        return userRepository.existsByUsername(username);
    }
}
