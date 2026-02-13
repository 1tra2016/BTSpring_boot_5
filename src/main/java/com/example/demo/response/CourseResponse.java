package com.example.demo.response;

import com.example.demo.enums.CourseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String title;
    private CourseStatus status;
    private CourseInstructorResponse instructor;
    public  CourseResponse(Long id, String title, CourseStatus status, CourseInstructorResponse courseInstructorResponse) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.instructor = courseInstructorResponse;
    }
}
