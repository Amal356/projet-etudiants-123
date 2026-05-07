package com.studentmanagement.controller;

import com.studentmanagement.dto.CreateStudentRequest;
import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Student management operations.
 * Exposes API endpoints for complete CRUD operations on students.
 * 
 * Requirements:
 * - 8.2: Controller layer using DTOs
 * - 8.10: Uses service layer for business logic
 * - 9.2, 9.3, 9.4, 9.5: Complete CRUD endpoints
 * - 10.1-10.5: HTTP methods with proper status codes
 * - 10.12: Swagger documentation
 * - 12.3: API documentation annotations
 */
@Tag(name = "Students", description = "Student management endpoints")
@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "*")
public class StudentController {
    
    private final StudentService studentService;
    
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    /**
     * Retrieves all students or filters by enrollment year.
     * 
     * @param annee optional enrollment year filter
     * @return ResponseEntity containing a list of students
     * 
     * Requirements:
     * - 9.2: GET endpoint for retrieving students
     * - 9.3: Optional query parameter for filtering by year
     * - 10.1: Returns HTTP 200 with student list
     */
    @Operation(summary = "Get all students", description = "Retrieves all students or filters by enrollment year")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved students")
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(
            @RequestParam(required = false) Integer annee) {
        if (annee != null) {
            return ResponseEntity.ok(studentService.findByAnneeInscription(annee));
        }
        return ResponseEntity.ok(studentService.findAll());
    }
    
    /**
     * Retrieves a single student by ID.
     * 
     * @param id the student ID
     * @return ResponseEntity containing the student
     * 
     * Requirements:
     * - 9.3: GET endpoint for single student
     * - 10.2: Returns HTTP 200 with student data
     * - 10.12: Returns HTTP 404 if not found
     */
    @Operation(summary = "Get student by ID", description = "Retrieves a single student by their ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved student")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }
    
    /**
     * Creates a new student.
     * 
     * @param request the student creation request with validation
     * @return ResponseEntity containing the created student
     * 
     * Requirements:
     * - 9.4: POST endpoint for creating students
     * - 10.3: Returns HTTP 201 Created with created resource
     * - 10.11: Validates request data
     */
    @Operation(summary = "Create new student", description = "Creates a new student with the provided data")
    @ApiResponse(responseCode = "201", description = "Student successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(
            @Valid @RequestBody CreateStudentRequest request) {
        StudentDTO created = studentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    /**
     * Updates an existing student.
     * 
     * @param id the student ID to update
     * @param request the updated student data with validation
     * @return ResponseEntity containing the updated student
     * 
     * Requirements:
     * - 9.5: PUT endpoint for updating students
     * - 10.4: Returns HTTP 200 with updated resource
     * - 10.12: Returns HTTP 404 if not found
     */
    @Operation(summary = "Update student", description = "Updates an existing student by ID")
    @ApiResponse(responseCode = "200", description = "Student successfully updated")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody CreateStudentRequest request) {
        StudentDTO updated = studentService.update(id, request);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Deletes a student by ID.
     * 
     * @param id the student ID to delete
     * @return ResponseEntity with no content
     * 
     * Requirements:
     * - 9.5: DELETE endpoint for removing students
     * - 10.5: Returns HTTP 204 No Content
     * - 10.12: Returns HTTP 404 if not found
     */
    @Operation(summary = "Delete student", description = "Deletes a student by ID")
    @ApiResponse(responseCode = "204", description = "Student successfully deleted")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
