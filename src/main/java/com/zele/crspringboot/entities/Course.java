package com.zele.crspringboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zele.crspringboot.tools.IDGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"expectedStudents", "enrolledStudents"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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
    @JsonIgnore
    private Set<Student> expectedStudents;

    @ManyToMany
    @JoinTable(
            name = "course_student_enrolled",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonIgnore
    private Set<Student> enrolledStudents;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public void setCourseCode() {
        this.courseName = this.courseName + String.valueOf(this.level).charAt(0) + "0" + IDGenerator.generateCourseID();
    }
}
