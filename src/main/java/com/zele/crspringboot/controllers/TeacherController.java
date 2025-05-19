package com.zele.crspringboot.controllers;

import com.zele.crspringboot.dtos.AddPasswordRequest;
import com.zele.crspringboot.dtos.ResetPasswordRequest;
import com.zele.crspringboot.dtos.student.UserCreateRequest;
import com.zele.crspringboot.dtos.student.UserViewDTO;
import com.zele.crspringboot.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/new")
    public ResponseEntity<UserViewDTO> createTeacher(@RequestBody UserCreateRequest createRequest) {
        return teacherService.userSignUp(createRequest);
    }

    @DeleteMapping("/{id}-delete")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        return teacherService.deleteUser(id);
    }

   @PutMapping("/{id}-add-password")
   public ResponseEntity<UserViewDTO> addPassword(
           @PathVariable Long id,
           @RequestBody AddPasswordRequest addPasswordRequest
   ) {
        return teacherService.addPassword(addPasswordRequest, id);
   }

   @PutMapping("/{id}-reset-password")
    public ResponseEntity<UserViewDTO> resetPassword(
            @PathVariable Long id,
            @RequestBody ResetPasswordRequest resetPasswordRequest
   ) {
        return teacherService.resetPassword(id, resetPasswordRequest);
   }
}