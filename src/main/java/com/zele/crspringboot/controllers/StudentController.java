package com.zele.crspringboot.controllers;

import com.zele.crspringboot.dtos.ResetPasswordRequest;
import com.zele.crspringboot.dtos.AddPasswordRequest;
import com.zele.crspringboot.dtos.course.CourseViewDTO;
import com.zele.crspringboot.dtos.student.StudentCreateRequest;
import com.zele.crspringboot.dtos.student.StudentViewDTO;
import com.zele.crspringboot.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping("/all")
    public List<StudentViewDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentViewDTO> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<StudentViewDTO> addStudent(
            @RequestBody StudentCreateRequest createRequest) {
        return studentService.studentSignUp(createRequest);
    }

    @DeleteMapping("/{id}-delete")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping("/{id}-add-password")
    public ResponseEntity<StudentViewDTO> addStudentPassword(
            @PathVariable Long id,
            @RequestBody AddPasswordRequest createRequest) {
        return studentService.addPassword(createRequest, id);
    }

    @PutMapping("/{id}-reset-password")
    public ResponseEntity<StudentViewDTO> resetStudentPassword(
            @PathVariable Long id,
            @RequestBody ResetPasswordRequest createRequest
    ) {
        return studentService.resetPassword(id, createRequest);
    }

    @PostMapping("/{id}-add-course")
    public ResponseEntity<CourseViewDTO> registerCourse(
            @RequestParam String courseName,
            @PathVariable Long id
    ) {
        return studentService.enrollCourse(courseName, id);
    }

    @DeleteMapping("/{id}-drop-course")
    public ResponseEntity<Void> dropCourse(
            @PathVariable Long id,
            @RequestParam String courseName
    ) {
        return studentService.dropCourse(courseName, id);
    }

    @GetMapping("/{id}-all-enrolled-courses")
    public List<CourseViewDTO> getAllEnrolledCourses(@PathVariable Long id) {
        return studentService.getAllEnrolledCourses(id);
    }

    @GetMapping("/{id}-all-expected-courses")
    public List<CourseViewDTO> getAllExpectedCourses(@PathVariable Long id) {
        return studentService.getAllExpectedCourses(id);
    }
}
