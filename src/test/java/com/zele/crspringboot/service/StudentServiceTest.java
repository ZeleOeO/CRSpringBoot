package com.zele.crspringboot.service;

import com.zele.crspringboot.dtos.AddPasswordRequest;
import com.zele.crspringboot.dtos.ResetPasswordRequest;
import com.zele.crspringboot.dtos.student.*;
import com.zele.crspringboot.entities.Student;
import com.zele.crspringboot.exceptions.EntityAlreadyExistsException;
import com.zele.crspringboot.exceptions.EntityNotAuthorizedException;
import com.zele.crspringboot.exceptions.EntityNotFoundException;
import com.zele.crspringboot.mappers.CourseMapper;
import com.zele.crspringboot.mappers.StudentMapper;
import com.zele.crspringboot.repositories.CourseRepository;
import com.zele.crspringboot.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;
    private CourseRepository courseRepository;
    private CourseMapper courseMapper;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        studentMapper = mock(StudentMapper.class);
        courseRepository = mock(CourseRepository.class);
        courseMapper = mock(CourseMapper.class);
        studentService = new StudentService(studentRepository, studentMapper, courseRepository, courseMapper);
    }

    @Test
    void testGetAllStudents() {
        Student student = new Student();
        student.setId(1L);
        UserViewDTO dto = new UserViewDTO();

        when(studentRepository.findAll()).thenReturn(List.of(student));
        when(studentMapper.toStudentViewDTO(student)).thenReturn(dto);

        List<UserViewDTO> result = studentService.getAllStudents();
        assertEquals(1, result.size());
    }

    @Test
    void testGetStudentById_Success() {
        Student student = new Student();
        student.setId(1L);
        UserViewDTO dto = new UserViewDTO();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentViewDTO(student)).thenReturn(dto);

        var response = studentService.getStudentById(1L);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> studentService.getStudentById(1L));
    }

    @Test
    void testDeleteStudent_Success() {
        Student student = new Student();
        student.setId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        var response = studentService.deleteStudent(1L);
        assertEquals(202, response.getStatusCodeValue());
        verify(studentRepository).deleteById(1L);
    }

    @Test
    void testDeleteStudent_NotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> studentService.deleteStudent(1L));
    }

    @Test
    void testCreateStudent_Success() {
        UserCreateRequest request = new UserCreateRequest();
        Student student = new Student();
        student.setEmail("test@test.com");

        when(studentMapper.createRequestToStudent(request)).thenReturn(student);
        when(studentRepository.findByEmail("test@test.com")).thenReturn(null);
        when(studentMapper.toStudentViewDTO(student)).thenReturn(new UserViewDTO());

        var response = studentService.studentSignUp(request);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void testCreateStudent_AlreadyExists() {
        UserCreateRequest request = new UserCreateRequest();
        Student student = new Student();
        student.setEmail("test@test.com");

        when(studentMapper.createRequestToStudent(request)).thenReturn(student);
        when(studentRepository.findByEmail("test@test.com")).thenReturn(student);

        assertThrows(EntityAlreadyExistsException.class, () -> studentService.studentSignUp(request));
    }

    @Test
    void testAddPassword_Success() {
        Student student = new Student();
        student.setId(1L);
        student.setPassword(null);

        AddPasswordRequest request = new AddPasswordRequest("pass123", "pass123");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentViewDTO(student)).thenReturn(new UserViewDTO());

        var response = studentService.addPassword(request, 1L);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("pass123", student.getPassword());
    }

    @Test
    void testAddPassword_AlreadySet() {
        Student student = new Student();
        student.setId(1L);
        student.setPassword("alreadySet");

        AddPasswordRequest request = new AddPasswordRequest("pass123", "pass123");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        assertThrows(EntityAlreadyExistsException.class, () -> studentService.addPassword(request, 1L));
    }

    @Test
    void testAddPassword_Mismatch() {
        Student student = new Student();
        student.setId(1L);
        student.setPassword(null);

        AddPasswordRequest request = new AddPasswordRequest("pass123", "wrong");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        assertThrows(EntityNotAuthorizedException.class, () -> studentService.addPassword(request, 1L));
    }

    @Test
    void testResetPassword_Success() {
        Student student = new Student();
        student.setId(1L);
        student.setPassword("oldPass");

        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setOldPassword("oldPass");
        request.setNewPassword("newPass");
        request.setConfirmNewPassword("newPass");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentViewDTO(student)).thenReturn(new UserViewDTO());

        var response = studentService.resetPassword(1L, request);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("newPass", student.getPassword());
    }

    @Test
    void testResetPassword_InvalidOldPassword() {
        Student student = new Student();
        student.setId(1L);
        student.setPassword("oldPass");

        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setOldPassword("wrong");
        request.setNewPassword("newPass");
        request.setConfirmNewPassword("newPass");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        assertThrows(EntityNotAuthorizedException.class, () -> studentService.resetPassword(1L, request));
    }

    @Test
    void testResetPassword_PasswordsDoNotMatch() {
        Student student = new Student();
        student.setId(1L);
        student.setPassword("oldPass");

        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setOldPassword("oldPass");
        request.setNewPassword("newPass");
        request.setConfirmNewPassword("mismatch");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        assertThrows(EntityNotAuthorizedException.class, () -> studentService.resetPassword(1L, request));
    }
}
