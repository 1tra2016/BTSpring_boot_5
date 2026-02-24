package com.example.demo.repositories;

import com.example.demo.enums.CourseStatus;
import com.example.demo.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("""
           SELECT c 
           FROM Course c
           WHERE c.status = :status
           """)
    Page<Course> findAllByStatus(
            @Param("status") CourseStatus status,
            Pageable pageable
    );
}