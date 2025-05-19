package com.zele.crspringboot.mappers;

import com.zele.crspringboot.dtos.student.UserCreateRequest;
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

   public Student createRequestToStudent(UserCreateRequest userCreateRequest) {
       Student student = new Student();
       student.setFirstName(userCreateRequest.getFirstName());
       student.setLastName(userCreateRequest.getLastName());
       student.setEmail(userCreateRequest.getEmail());
       return student;
   }
}
