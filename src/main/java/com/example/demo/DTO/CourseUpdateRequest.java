package com.example.demo.DTO;

import com.example.demo.enums.CourseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseUpdateRequest {
    private String title;
    private CourseStatus status;
    private Long instructorId;
}
