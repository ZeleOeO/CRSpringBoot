package com.zele.crspringboot.repositories;

import com.zele.crspringboot.entities.Course;
import com.zele.crspringboot.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseName(String courseName);
    Course findByCourseName(String courseName);
    Course findByTeacher(Teacher teacher);
}
