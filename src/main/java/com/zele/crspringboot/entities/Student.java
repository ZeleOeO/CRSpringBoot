package com.zele.crspringboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"coursesEnrolled", "coursesExpectedToBeEnrolled"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "enrolledStudents")
    @JsonIgnore
    private Set<Course> coursesEnrolled = new HashSet<>();

    @ManyToMany(mappedBy = "expectedStudents")
    @JsonIgnore
    private Set<Course> coursesExpectedToBeEnrolled = new HashSet<>();
}
