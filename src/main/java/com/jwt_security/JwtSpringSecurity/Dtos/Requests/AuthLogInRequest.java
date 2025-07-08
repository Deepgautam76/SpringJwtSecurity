package com.jwt_security.JwtSpringSecurity.Dtos.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthLogInRequest {
    private String username;
    private String password;
}
