package com.zele.crspringboot.exceptions.course;

public class CourseAlreadyEnrolledByUserException extends RuntimeException {
    public CourseAlreadyEnrolledByUserException(String message) {
        super(message);
    }
}
