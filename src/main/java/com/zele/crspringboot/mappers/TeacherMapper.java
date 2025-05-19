package com.zele.crspringboot.mappers;

import com.zele.crspringboot.dtos.student.UserCreateRequest;
import com.zele.crspringboot.dtos.student.UserViewDTO;
import com.zele.crspringboot.entities.Teacher;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    public UserViewDTO toUserViewDTO(Teacher teacher) {
        UserViewDTO userViewDTO = new UserViewDTO();
        userViewDTO.setId(teacher.getId());
        userViewDTO.setFirstName(teacher.getFirstName());
        userViewDTO.setLastName(teacher.getLastName());
        userViewDTO.setEmail(teacher.getEmail());
        return userViewDTO;
    }

    public Teacher createRequestToStudent(UserCreateRequest userCreateRequest) {
       Teacher teacher = new Teacher();
       teacher.setFirstName(userCreateRequest.getFirstName());
       teacher.setLastName(userCreateRequest.getLastName());
       teacher.setEmail(userCreateRequest.getEmail());
       return teacher;
    }
}
