package com.example.demo.controllers;

import com.example.demo.DTO.StudentEnrollmentRequest;
import com.example.demo.models.StudentEnrollment;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.StudentEnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-enrollments")
public class StudentEnrollmentController {

    private final StudentEnrollmentService studentEnrollmentService;

    public StudentEnrollmentController(StudentEnrollmentService studentEnrollmentService) {
        this.studentEnrollmentService = studentEnrollmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentEnrollment>>> getAllEnrollments() {
        try {
            List<StudentEnrollment> studentEnrollments = studentEnrollmentService.getAllEnrollments();
            return ResponseEntity.ok(ApiResponse.success("Thành công", studentEnrollments));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> getEnrollmentById(@PathVariable long id) {
        try {
            StudentEnrollment studentEnrollment = studentEnrollmentService.getEnrollmentById(id);
            return ResponseEntity.ok(ApiResponse.success("Thành công", studentEnrollment));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentEnrollment>> createEnrollment(
            @RequestBody StudentEnrollmentRequest req
    ) {
        try {
            StudentEnrollment created = studentEnrollmentService.createEnrollment(req);
            return ResponseEntity.ok(ApiResponse.success("Thành công", created));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> updateEnrollment(
            @PathVariable long id,
            @RequestBody StudentEnrollment newStudentEnrollment
    ) {
        try {
            StudentEnrollment updated =
                    studentEnrollmentService.updateEnrollment(id, newStudentEnrollment);

            return ResponseEntity.ok(ApiResponse.success("Thành công", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEnrollment(@PathVariable long id) {
        try {
            studentEnrollmentService.deleteEnrollmentById(id);
            return ResponseEntity.ok(ApiResponse.success("Thành công", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
