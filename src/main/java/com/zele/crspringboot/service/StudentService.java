package com.zele.crspringboot.service;

import com.zele.crspringboot.dtos.ResetPasswordRequest;
import com.zele.crspringboot.dtos.AddPasswordRequest;
import com.zele.crspringboot.dtos.course.CourseViewDTO;
import com.zele.crspringboot.dtos.student.StudentCreateRequest;
import com.zele.crspringboot.dtos.student.StudentViewDTO;
import com.zele.crspringboot.entities.Student;
import com.zele.crspringboot.exceptions.EntityAlreadyExistsException;
import com.zele.crspringboot.exceptions.EntityNotAuthorizedException;
import com.zele.crspringboot.exceptions.EntityNotFoundException;
import com.zele.crspringboot.mappers.CourseMapper;
import com.zele.crspringboot.mappers.StudentMapper;
import com.zele.crspringboot.repositories.CourseRepository;
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
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<StudentViewDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentViewDTO)
                .toList();
    }

    public ResponseEntity<StudentViewDTO> getStudentById(Long id) {
        var user = studentRepository.findById(id).orElse(null);
        if (user == null) throw new EntityNotFoundException("Student with id " + id + " not found");
        return ResponseEntity.status(HttpStatus.OK).body(studentMapper.toStudentViewDTO(user));
    }

    public ResponseEntity<Void> deleteStudent(Long id) {
        var user = studentRepository.findById(id).orElse(null);
        if (user == null) throw new EntityNotFoundException("Student with id " + id + " not found");
        studentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    public ResponseEntity<StudentViewDTO> studentSignUp(StudentCreateRequest studentCreateRequest) {
        Student student = studentMapper.createRequestToStudent(studentCreateRequest);
        if (studentRepository.findByEmail(student.getEmail()) != null) throw new EntityAlreadyExistsException("Student already exists");
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.toStudentViewDTO(student));
    }

    public ResponseEntity<StudentViewDTO> addPassword(AddPasswordRequest addPasswordRequest, Long Id) {
        var user = studentRepository.findById(Id).orElse(null);
        if (user == null) throw new EntityNotFoundException("Student not found");
        if (user.getPassword() != null) throw new EntityAlreadyExistsException("Password already exists \nTry Reset-Password");
        if (!addPasswordRequest.getPassword().equals(addPasswordRequest.getConfirmPassword())) throw new EntityNotAuthorizedException("Passwords do not match");
        user.setPassword(addPasswordRequest.getPassword());
        studentRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.toStudentViewDTO(user));
    }

    public ResponseEntity<StudentViewDTO> resetPassword(Long Id, ResetPasswordRequest resetPasswordRequest) {
        var user = studentRepository.findById(Id).orElse(null);
        if (user == null) throw new EntityNotFoundException("Student not found");
        if (user.getPassword() == null) {
            addPassword(new AddPasswordRequest(resetPasswordRequest.getNewPassword(), resetPasswordRequest.getConfirmNewPassword()), user.getId());
            return ResponseEntity.status(HttpStatus.OK).body(studentMapper.toStudentViewDTO(user));
        }
        if (!resetPasswordRequest.validate()) throw new EntityNotAuthorizedException("Passwords do not match");
        if (!resetPasswordRequest.getOldPassword().equals(user.getPassword())) throw new EntityNotAuthorizedException("Passwords do not match");
        user.setPassword(resetPasswordRequest.getNewPassword());
        studentRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.toStudentViewDTO(user));
    }

    public ResponseEntity<CourseViewDTO> enrollCourse(String courseName, Long Id) {
        var user = studentRepository.findById(Id).orElse(null);
        var course = courseRepository.findByCourseName(courseName);
        if (user == null) throw new EntityNotFoundException("Student not found");
        if (course == null) throw new EntityNotFoundException("Course not found");
        if (course.getEnrolledStudents().contains(user)) throw new EntityAlreadyExistsException("Course already exists");
        course.getEnrolledStudents().add(user);
        studentRepository.save(user);
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.OK).body(courseMapper.courseViewDTO(course));
    }

    public ResponseEntity<Void> dropCourse(String courseName, Long Id) {
        var user = studentRepository.findById(Id).orElse(null);
        var course = courseRepository.findByCourseName(courseName);
        if (user == null) throw new EntityNotFoundException("Student not found");
        if (course == null) throw new EntityNotFoundException("Course not found");
        if (!user.getCoursesEnrolled().contains(course)) throw new EntityNotAuthorizedException("Course not enrolled");
        course.getEnrolledStudents().remove(user);
        studentRepository.save(user);
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<CourseViewDTO> getAllEnrolledCourses(Long id) {
        var user = studentRepository.findById(id).orElse(null);
        if (user == null) throw new EntityNotFoundException("Student not found");
        return user.getCoursesEnrolled().
                stream()
                .map(courseMapper::courseViewDTO)
                .toList();
    }

    public List<CourseViewDTO> getAllExpectedCourses(Long id) {
        var user = studentRepository.findById(id).orElse(null);
        if (user == null) throw new EntityNotFoundException("Student not found");
        return user.getCoursesExpectedToBeEnrolled().
                stream()
                .map(courseMapper::courseViewDTO)
                .toList();
    }
}
