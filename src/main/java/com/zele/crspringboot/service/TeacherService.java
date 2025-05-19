package com.zele.crspringboot.service;

import com.zele.crspringboot.dtos.AddPasswordRequest;
import com.zele.crspringboot.dtos.ResetPasswordRequest;
import com.zele.crspringboot.dtos.student.UserCreateRequest;
import com.zele.crspringboot.dtos.student.UserViewDTO;
import com.zele.crspringboot.entities.Teacher;
import com.zele.crspringboot.dtos.student.UserViewDTO;
import com.zele.crspringboot.exceptions.EntityAlreadyExistsException;
import com.zele.crspringboot.exceptions.EntityNotAuthorizedException;
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

    public ResponseEntity<Void> deleteUser(Long id) {
        var teacher = teacherRepository.findById(id).orElse(null);
        if (teacher == null) throw new EntityNotFoundException("Teacher with id " + id + " not found");
        teacherRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    public ResponseEntity<UserViewDTO> userSignUp(UserCreateRequest userCreateRequest) {
        Teacher teacher = teacherMapper.createRequestToStudent(userCreateRequest);
        if (teacherRepository.findByEmail(teacher.getEmail()) != null) throw new EntityAlreadyExistsException("Teacher already exists");
        teacherRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherMapper.toUserViewDTO(teacher));
    }

    public ResponseEntity<UserViewDTO> addPassword(AddPasswordRequest addPasswordRequest, Long Id) {
        var user = teacherRepository.findById(Id).orElse(null);
        if (user == null) throw new EntityNotFoundException("Student not found");
        if (user.getPassword() != null) throw new EntityAlreadyExistsException("Password already exists \nTry Reset-Password");
        if (!addPasswordRequest.getPassword().equals(addPasswordRequest.getConfirmPassword())) throw new EntityNotAuthorizedException("Passwords do not match");
        user.setPassword(addPasswordRequest.getPassword());
        teacherRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherMapper.toUserViewDTO(user));
    }

    public ResponseEntity<UserViewDTO> resetPassword(Long Id, ResetPasswordRequest resetPasswordRequest) {
        var user = teacherRepository.findById(Id).orElse(null);
        if (user == null) throw new EntityNotFoundException("Teacher not found");
        if (user.getPassword() == null) {
            addPassword(new AddPasswordRequest(resetPasswordRequest.getNewPassword(), resetPasswordRequest.getConfirmNewPassword()), user.getId());
            return ResponseEntity.status(HttpStatus.OK).body(teacherMapper.toUserViewDTO(user));
        }
        if (!resetPasswordRequest.validate()) throw new EntityNotAuthorizedException("Passwords do not match");
        if (!resetPasswordRequest.getOldPassword().equals(user.getPassword())) throw new EntityNotAuthorizedException("Passwords do not match");
        user.setPassword(resetPasswordRequest.getNewPassword());
        teacherRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherMapper.toUserViewDTO(user));
    }
}
