package com.example.demo.response;

import com.example.demo.enums.CourseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;
    private CourseInstructorResponse instructor;
    public  CourseResponseV2(Long id, String title, CourseStatus status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }
}
