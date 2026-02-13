package com.example.demo.controllers;

import com.example.demo.DTO.InstructorCreateRequest;
import com.example.demo.models.Instructor;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructor(
            @RequestParam(required = false) String search
    ) {
        List<Instructor> instructors = instructorService.getAllInstructor(search);

        try {
            return ResponseEntity.ok(ApiResponse.success("Thành công", instructors));
        }catch(RuntimeException e) {
            return  ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable long id) {
        Instructor instructor = instructorService.getInstructorById(id);
        try {
            return ResponseEntity.ok(ApiResponse.success("Thành công", instructor));
        }catch(RuntimeException e) {
            return  ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createInstructor(@RequestBody InstructorCreateRequest infor){
        try {
            Instructor instructor = instructorService.createInstructor(infor);
            return ResponseEntity.ok(ApiResponse.success("Thành công", instructor));
        }catch(RuntimeException e) {
            return  ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable Long id, @RequestBody Instructor newInstructor) {
        Instructor instructor = instructorService.updateInstructor(id, newInstructor);
        try {
            return ResponseEntity.ok(ApiResponse.success("Thành công", instructor));
        }catch(RuntimeException e) {
            return  ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>>  deleteInstructorById(@PathVariable Long id) {
        instructorService.deleteInstructorById(id);
        try {
            return ResponseEntity.ok(ApiResponse.success("Thành công", null));
        }catch(RuntimeException e) {
            return  ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        }

    }
}
