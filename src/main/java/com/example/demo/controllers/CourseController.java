package com.example.demo.controllers;

import com.example.demo.DTO.CourseCreateRequest;
import com.example.demo.enums.CourseStatus;
import com.example.demo.response.CourseResponse;
import com.example.demo.DTO.CourseUpdateRequest;
import com.example.demo.models.Course;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.CourseResponseV2;
import com.example.demo.response.PageResponse;
import com.example.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

//    @GetMapping
//    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getAllCourses(
//            @RequestParam(required = false) String search,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(required = false) String sortBy,
//            @RequestParam(defaultValue = "DESC") Sort.Direction direction
//    ) {
//        PageResponse<CourseResponse> result =
//                courseService.getPagedCourses(page, size, sortBy, direction);
//        return ResponseEntity.ok(ApiResponse.success("Thành công", result));
//    }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<Course>> getCourseById (@PathVariable Long id){
            Course course = courseService.getCourseById(id);
            try {
                return ResponseEntity.ok(ApiResponse.success("Thành công", course));
            } catch (RuntimeException e) {
                return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
            }
        }

        @PostMapping
        public ResponseEntity<ApiResponse<Course>> createCourse (@RequestBody CourseCreateRequest req){
            Course course = courseService.createCourse(req);
            try {
                return ResponseEntity.ok(ApiResponse.success("Thành công", course));
            } catch (RuntimeException e) {
                return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<Course>> updateCourse (
        @PathVariable long id,
        @RequestBody CourseUpdateRequest req
            ){
            Course course = courseService.updateCourse(id, req);
            try {
                return ResponseEntity.ok(ApiResponse.success("Thành công", course));
            } catch (RuntimeException e) {
                return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
            }

        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Course>> deleteCourse ( @PathVariable long id){
            courseService.deleteCourse(id);
            try {
                return ResponseEntity.ok(ApiResponse.success("Thành công", null));
            } catch (RuntimeException e) {
                return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
            }
        }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseV2>>> getPagedCoursesByStatusV2(
            @RequestParam(defaultValue = "ACTIVE") CourseStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        PageResponse<CourseResponseV2> result =
                courseService.getPagedCoursesByStatusV2(page, size, sortBy, direction, status);
        return ResponseEntity.ok(ApiResponse.success("Thành công", result));
    }
}