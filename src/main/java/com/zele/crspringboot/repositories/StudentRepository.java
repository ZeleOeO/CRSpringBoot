package com.zele.crspringboot.repositories;

import com.zele.crspringboot.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);
}