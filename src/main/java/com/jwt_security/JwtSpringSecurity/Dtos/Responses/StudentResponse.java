package com.jwt_security.JwtSpringSecurity.Dtos.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private String name;
    private int rollNo;
    private int age;
}
