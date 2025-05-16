package com.zele.crspringboot.service;

import com.zele.crspringboot.dtos.student.AddPasswordRequest;
import com.zele.crspringboot.dtos.student.StudentCreateRequest;
import com.zele.crspringboot.dtos.student.StudentViewDTO;
import com.zele.crspringboot.entities.Student;
import com.zele.crspringboot.mappers.StudentMapper;
import com.zele.crspringboot.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentViewDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentViewDTO)
                .toList();
    }

    public ResponseEntity<StudentViewDTO> getStudentById(Long id) {
        var user = studentRepository.findById(id).orElse(null);
        if (user == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        return ResponseEntity.status(HttpStatus.OK).body(studentMapper.toStudentViewDTO(user));
    }

    public ResponseEntity<Void> deleteStudent(Long id) {
        var user = studentRepository.findById(id).orElse(null);
        if (user == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        studentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    public ResponseEntity<StudentViewDTO> createStudent(StudentCreateRequest studentCreateRequest) {
        Student student = studentMapper.createRequestToStudent(studentCreateRequest);
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.toStudentViewDTO(student));
    }

    public ResponseEntity<StudentViewDTO> addPassword(AddPasswordRequest addPasswordRequest) {
        var user = studentRepository.findById(addPasswordRequest.getId()).orElse(null);
        if (user == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (!addPasswordRequest.getPassword().equals(addPasswordRequest.getConfirmPassword())) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        user.setPassword(addPasswordRequest.getPassword());
        studentRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.toStudentViewDTO(user));
    }

}
