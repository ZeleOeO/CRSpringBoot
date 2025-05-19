package com.zele.crspringboot.controllers;

import com.zele.crspringboot.dtos.course.CourseCreateRequest;
import com.zele.crspringboot.dtos.course.CourseViewDTO;
import com.zele.crspringboot.service.CourseService;
import com.zele.crspringboot.service.StudentService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/all")
    public List<CourseViewDTO> getCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseViewDTO> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
}
