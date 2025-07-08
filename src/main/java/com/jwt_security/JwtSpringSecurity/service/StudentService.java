package com.jwt_security.JwtSpringSecurity.service;

import com.jwt_security.JwtSpringSecurity.Dtos.Requests.StudentRequest;
import com.jwt_security.JwtSpringSecurity.Dtos.Responses.StudentResponse;
import com.jwt_security.JwtSpringSecurity.model.Student;
import com.jwt_security.JwtSpringSecurity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<?> addStudent(StudentRequest request) throws Exception {
        if(request==null){
            throw new Exception("Can't add null object");
        }
        Student student=new Student();
        student.setName(request.getName());
        student.setRollNo(request.getRollNo());
        student.setAge(request.getAge());
        studentRepository.save(student);
        return new ResponseEntity<>("Add success fully",HttpStatus.OK);
    }

    public ResponseEntity<?> getAllStudent() {
        List<Student> students=studentRepository.findAll();
        List<StudentResponse> responseList=new ArrayList<>();
        for(Student student:students){
            StudentResponse s1=new StudentResponse();
            s1.setName(student.getName());
            s1.setRollNo(student.getRollNo());
            s1.setAge(student.getAge());
            responseList.add(s1);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
