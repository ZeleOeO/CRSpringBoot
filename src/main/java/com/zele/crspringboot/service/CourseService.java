package com.zele.crspringboot.service;

import com.zele.crspringboot.dtos.course.CourseCreateRequest;
import com.zele.crspringboot.dtos.course.CourseViewDTO;
import com.zele.crspringboot.entities.Course;
import com.zele.crspringboot.exceptions.EntityAlreadyExistsException;
import com.zele.crspringboot.exceptions.EntityNotFoundException;
import com.zele.crspringboot.mappers.CourseMapper;
import com.zele.crspringboot.repositories.CourseRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<CourseViewDTO> getAllCourses() {
       return courseRepository.findAll()
               .stream()
               .map(courseMapper::courseViewDTO)
               .toList();
    }

    public ResponseEntity<CourseViewDTO> getCourseById(Long id) {
        var course = courseRepository.findById(id).orElse(null);
        if (course == null) throw new EntityNotFoundException("Course not found");
        return ResponseEntity.status(HttpStatus.OK).body(courseMapper.courseViewDTO(course));
    }


}
