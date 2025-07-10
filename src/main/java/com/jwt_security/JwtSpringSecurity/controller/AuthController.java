package com.jwt_security.JwtSpringSecurity.controller;

import com.jwt_security.JwtSpringSecurity.Dtos.Requests.AuthLogInRequest;
import com.jwt_security.JwtSpringSecurity.Dtos.Requests.AuthSignUpRequestDto;
import com.jwt_security.JwtSpringSecurity.Dtos.Responses.AuthResponse;
import com.jwt_security.JwtSpringSecurity.Jwt.JwtUtilities;
import com.jwt_security.JwtSpringSecurity.model.User;
import com.jwt_security.JwtSpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtilities jwtUtilities;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody AuthSignUpRequestDto request) throws Exception {
        try {
            if(request==null || request.getUsername().isEmpty()){
                throw new Exception("Not enter blank user name or password");
            }
            if(userService.isExist(request.getUsername())){
                throw new Exception("User already exist");
            }
            String response=userService.registerUser(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Encountering Error is "+e,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/signIn")
    public ResponseEntity<?> signInUser(@RequestBody AuthLogInRequest request){
        try{
            if(request == null || request.getUsername().isBlank() || request.getPassword().isBlank()) {
                return new ResponseEntity<>("Username or password cannot be blank", HttpStatus.BAD_REQUEST);
            }

            // Authenticate the user before SingUp(Login)
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken=jwtUtilities.jwtTokenGenerate(userDetails.getUsername());

            AuthResponse response=new AuthResponse();
            response.setJwtToken(jwtToken);
            response.setMessage("Login success");

            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (BadCredentialsException ex){
            return new ResponseEntity<>("Bad credential",HttpStatus.UNAUTHORIZED);
        }
    }
}
