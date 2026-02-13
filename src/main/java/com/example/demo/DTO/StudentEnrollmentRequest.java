package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentEnrollmentRequest {
    private Long courseId;
    private Long studentId;
}
