package com.zele.crspringboot.service;

import com.zele.crspringboot.dtos.student.UserViewDTO;
import com.zele.crspringboot.entities.Teacher;
import com.zele.crspringboot.exceptions.EntityNotFoundException;
import com.zele.crspringboot.mappers.TeacherMapper;
import com.zele.crspringboot.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public List<UserViewDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toUserViewDTO)
                .toList();
    }

    public ResponseEntity<UserViewDTO> getTeacherById(Long id) {
        var teacher = teacherRepository.findById(id).orElse(null);
        if (teacher == null) throw new EntityNotFoundException("Teacher not found");
        return ResponseEntity.status(HttpStatus.OK).body(teacherMapper.toUserViewDTO(teacher));
    }
}
