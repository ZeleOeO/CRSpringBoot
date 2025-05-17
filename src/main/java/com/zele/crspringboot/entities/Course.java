package com.zele.crspringboot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private int capacity;
    private int level;

    @ManyToMany
    @JoinTable(
            name = "course_student_expected",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> expectedStudents;

    @ManyToMany
    @JoinTable(
            name = "course_student_enrolled",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> enrolledStudents;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    
    public void setCourseName() {
        this.courseName = this.courseName + String.valueOf(level).charAt(0) + "0" + String.valueOf(this.id);;
    }
}
