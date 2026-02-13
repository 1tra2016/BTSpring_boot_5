package com.example.demo.services;


import com.example.demo.DTO.CourseCreateRequest;
import com.example.demo.DTO.CourseInstructorResponse;
import com.example.demo.DTO.CourseResponse;
import com.example.demo.DTO.CourseUpdateRequest;
import com.example.demo.models.Instructor;
import com.example.demo.models.Course;
import com.example.demo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.NoSuchElementException;

@org.springframework.stereotype.Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorService instructorService;

    @Autowired
    public CourseService(CourseRepository courseRepository, InstructorService instructorService) {
        this.courseRepository = courseRepository;
        this.instructorService = instructorService;
    }

    public Page<CourseResponse> getPagedCourses(
            int page,
            int size,
            String sortBy,
            Sort.Direction direction
    ) {

        if (page < 0) {
            page = 0;
        }

        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }

        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Course> coursePage = courseRepository.findAll(pageable);

        return coursePage.map(this::mapToResponse);
    }

    private CourseResponse mapToResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getStatus(),
                new CourseInstructorResponse(
                        course.getInstructor().getId(),
                        course.getInstructor().getInstructorName()
                )
        );
    }

    public Course getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found id: " + id));
        return course;
    }

    public Course createCourse(CourseCreateRequest req) {
        Instructor instructor = instructorService.getInstructorById(req.getInstructorId()); //getInstructorById sẽ ném RuntimeException nếu null
        Course course =  new Course();
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, CourseUpdateRequest req) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found id: " + id));

        Instructor instructor = instructorService.getInstructorById(req.getInstructorId());
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public boolean deleteCourse(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found id: " + id));
        courseRepository.deleteById(id);
        return true;
    }
}
