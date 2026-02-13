package com.example.demo.DTO;

import com.example.demo.enums.CourseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreateRequest {
    private String title;
    private CourseStatus status;
    private Long instructorId;
}
