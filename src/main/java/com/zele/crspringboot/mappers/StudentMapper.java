package com.zele.crspringboot.mappers;

import com.zele.crspringboot.dtos.student.StudentCreateRequest;
import com.zele.crspringboot.dtos.student.UserViewDTO;
import com.zele.crspringboot.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
   public UserViewDTO toStudentViewDTO(Student student) {
       UserViewDTO userViewDTO = new UserViewDTO();
       userViewDTO.setId(student.getId());
       userViewDTO.setFirstName(student.getFirstName());
       userViewDTO.setLastName(student.getLastName());
       userViewDTO.setEmail(student.getEmail());
       return userViewDTO;
   }

   public Student createRequestToStudent(StudentCreateRequest studentCreateRequest) {
       Student student = new Student();
       student.setFirstName(studentCreateRequest.getFirstName());
       student.setLastName(studentCreateRequest.getLastName());
       student.setEmail(studentCreateRequest.getEmail());
       return student;
   }
}
