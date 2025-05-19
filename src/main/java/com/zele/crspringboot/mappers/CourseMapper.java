package com.zele.crspringboot.mappers;

import com.zele.crspringboot.dtos.course.CourseCreateRequest;
import com.zele.crspringboot.dtos.course.CourseViewDTO;
import com.zele.crspringboot.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseViewDTO courseViewDTO(Course course) {
        CourseViewDTO courseViewDTO = new CourseViewDTO();
        courseViewDTO.setId(course.getId());
        courseViewDTO.setCourseName(course.getCourseName());
        courseViewDTO.setCapacity(course.getCapacity());
        courseViewDTO.setLevel(course.getLevel());
        return courseViewDTO;
    }

    public Course createCourseByRequest(CourseCreateRequest createRequest) {
        Course course = new Course();
        course.setCapacity(createRequest.getCapacity());
        course.setLevel(createRequest.getLevel());
        course.setCourseName(createRequest.getCourseName());
        course.setCourseCode();
        return course;
    }
}
