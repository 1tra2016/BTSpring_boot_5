package com.example.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseInstructorResponse {
    private Long id;
    private String name;
    public  CourseInstructorResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
