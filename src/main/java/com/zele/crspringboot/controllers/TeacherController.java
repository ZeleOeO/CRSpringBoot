package com.zele.crspringboot.controllers;

import com.zele.crspringboot.dtos.student.UserViewDTO;
import com.zele.crspringboot.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/all")
    public List<UserViewDTO> getTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserViewDTO> getTeacher(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }
}
