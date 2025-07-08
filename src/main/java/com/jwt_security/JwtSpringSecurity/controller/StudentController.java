package com.jwt_security.JwtSpringSecurity.controller;

import com.jwt_security.JwtSpringSecurity.Dtos.Requests.StudentRequest;
import com.jwt_security.JwtSpringSecurity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody StudentRequest request) throws Exception {
        return studentService.addStudent(request);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getAllStudent(){
        return studentService.getAllStudent();
    }


}
