package com.zele.crspringboot.mappers;

import com.zele.crspringboot.dtos.student.StudentCreateRequest;
import com.zele.crspringboot.dtos.student.StudentViewDTO;
import com.zele.crspringboot.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
   public StudentViewDTO toStudentViewDTO(Student student) {
       StudentViewDTO studentViewDTO = new StudentViewDTO();
       studentViewDTO.setId(student.getId());
       studentViewDTO.setFirstName(student.getFirstName());
       studentViewDTO.setLastName(student.getLastName());
       studentViewDTO.setEmail(student.getEmail());
       return studentViewDTO;
   }

   public Student createRequestToStudent(StudentCreateRequest studentCreateRequest) {
       Student student = new Student();
       student.setFirstName(studentCreateRequest.getFirstName());
       student.setLastName(studentCreateRequest.getLastName());
       student.setEmail(studentCreateRequest.getEmail());
       return student;
   }
}
