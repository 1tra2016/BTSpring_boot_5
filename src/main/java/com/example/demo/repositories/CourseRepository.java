package com.example.demo.repositories;

import com.example.demo.enums.CourseStatus;
import com.example.demo.models.Course;
import com.example.demo.response.CourseResponseV2;
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

    @Query("""
           SELECT new com.example.demo.response.CourseResponseV2(
                      c.id,
                      c.title,
                      c.status          
           )                 
           FROM Course c
           WHERE c.status = :status
           """)
    Page<CourseResponseV2> findAllByStatusV2(
            @Param("status") CourseStatus status,
            Pageable pageable
    );
}