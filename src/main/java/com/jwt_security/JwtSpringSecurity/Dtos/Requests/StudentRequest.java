package com.jwt_security.JwtSpringSecurity.Dtos.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    private String name;
    private int rollNo;
    private int age;
}
