package com.zele.crspringboot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "expectedStudents")
    private HashSet<Course> coursesEnrolled;

    @ManyToMany(mappedBy = "enrolledStudents")
    private HashSet<Course> coursesExpectedToBeEnrolled;
}
