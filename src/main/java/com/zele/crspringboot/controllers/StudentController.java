package com.zele.crspringboot.controllers;

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
    public ResponseEntity<StudentViewDTO> addStudent(StudentCreateRequest createRequest) {
        return studentService.createStudent(createRequest);
    }

    @DeleteMapping("/{id}-delete")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping("{id}-add-password")
    public ResponseEntity<StudentViewDTO> addStudentPassword(@PathVariable Long id, StudentAddPasswordRequest createRequest) {
        return studentService.addPassword(createRequest);
    }
}
