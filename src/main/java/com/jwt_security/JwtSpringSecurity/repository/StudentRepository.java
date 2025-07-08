package com.jwt_security.JwtSpringSecurity.repository;

import com.jwt_security.JwtSpringSecurity.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
