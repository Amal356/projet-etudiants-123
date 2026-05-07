package com.studentmanagement.controller;

import com.studentmanagement.dto.DepartmentDTO;
import com.studentmanagement.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Department management operations.
 * Exposes API endpoints for complete CRUD operations on departments.
 * 
 * Requirements:
 * - 8.2: Controller layer using DTOs
 * - 10.6-10.10: Complete CRUD operations for departments
 * - 12.3: Swagger documentation annotations
 */
@Tag(name = "Departments", description = "Department management endpoints")
@RestController
@RequestMapping("/api/departements")
public class DepartmentController {
    
    private final DepartmentService departmentService;
    
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    /**
     * Retrieves all departments.
     * 
     * @return ResponseEntity containing a list of all departments
     * 
     * Requirements:
     * - 10.6: GET endpoint returning all departments with HTTP 200
     */
    @Operation(summary = "Get all departments", description = "Retrieves all departments from the database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved departments")
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.findAll());
    }
    
    /**
     * Retrieves a single department by ID.
     * 
     * @param id the department ID
     * @return ResponseEntity containing the department
     * 
     * Requirements:
     * - 10.7: GET endpoint returning single department with HTTP 200
     * - 10.12: Returns HTTP 404 if not found
     */
    @Operation(summary = "Get department by ID", description = "Retrieves a single department by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved department")
    @ApiResponse(responseCode = "404", description = "Department not found")
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }
    
    /**
     * Creates a new department.
     * 
     * @param dto the department creation request with validation
     * @return ResponseEntity containing the created department
     * 
     * Requirements:
     * - 10.8: POST endpoint creating department and returning HTTP 201
     * - 10.11: Validates request data
     */
    @Operation(summary = "Create new department", description = "Creates a new department with the provided data")
    @ApiResponse(responseCode = "201", description = "Department successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(
            @Valid @RequestBody DepartmentDTO dto) {
        DepartmentDTO created = departmentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    /**
     * Updates an existing department.
     * 
     * @param id the department ID to update
     * @param dto the updated department data with validation
     * @return ResponseEntity containing the updated department
     * 
     * Requirements:
     * - 10.9: PUT endpoint updating department and returning HTTP 200
     * - 10.12: Returns HTTP 404 if not found
     */
    @Operation(summary = "Update department", description = "Updates an existing department by ID")
    @ApiResponse(responseCode = "200", description = "Department successfully updated")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    @ApiResponse(responseCode = "404", description = "Department not found")
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentDTO dto) {
        DepartmentDTO updated = departmentService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Deletes a department by ID.
     * 
     * @param id the department ID to delete
     * @return ResponseEntity with no content
     * 
     * Requirements:
     * - 10.10: DELETE endpoint deleting department and returning HTTP 204
     * - 10.12: Returns HTTP 404 if not found
     */
    @Operation(summary = "Delete department", description = "Deletes a department by ID")
    @ApiResponse(responseCode = "204", description = "Department successfully deleted")
    @ApiResponse(responseCode = "404", description = "Department not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
