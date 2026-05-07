package com.studentmanagement.service;

import com.studentmanagement.dto.CreateStudentRequest;
import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.entity.Department;
import com.studentmanagement.entity.Student;
import com.studentmanagement.exception.ResourceNotFoundException;
import com.studentmanagement.mapper.StudentMapper;
import com.studentmanagement.repository.DepartmentRepository;
import com.studentmanagement.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for Student entity business logic.
 * Implements CRUD operations with caching and logging.
 * 
 * Requirements:
 * - 8.3: Service layer with business logic
 * - 8.10: Uses mapper for DTO/Entity conversion
 * - 9.1: Custom query by enrollment year
 * - 10.1-10.5: Complete CRUD operations
 * - 13.3, 13.4, 13.7, 13.8, 13.9: Caching with Redis
 */
@Service
public class StudentService {
    
    private static final Logger log = LoggerFactory.getLogger(StudentService.class);
    
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentMapper studentMapper;
    
    public StudentService(StudentRepository studentRepository, DepartmentRepository departmentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.studentMapper = studentMapper;
    }
    
    /**
     * Retrieves all students from the database.
     * Results are cached to improve performance.
     * 
     * @return list of all students as DTOs
     */
    @Cacheable(value = "students")
    @Transactional(readOnly = true)
    public List<StudentDTO> findAll() {
        log.info("Fetching all students from database");
        return studentRepository.findAll().stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves a single student by ID.
     * Result is cached with the student ID as the cache key.
     * 
     * @param id the student ID
     * @return the student DTO
     * @throws ResourceNotFoundException if student not found
     */
    @Cacheable(value = "students", key = "#id")
    @Transactional(readOnly = true)
    public StudentDTO findById(Long id) {
        log.info("Fetching student with id: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return studentMapper.toDTO(student);
    }
    
    /**
     * Retrieves students by their first enrollment year.
     * Results are cached with the year as the cache key.
     * 
     * @param annee the enrollment year
     * @return list of students enrolled in the specified year
     */
    @Cacheable(value = "students", key = "'year-' + #annee")
    @Transactional(readOnly = true)
    public List<StudentDTO> findByAnneeInscription(int annee) {
        log.info("Fetching students enrolled in year: {}", annee);
        return studentRepository.findByAnneePremiereInscription(annee).stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Creates a new student.
     * Evicts all student cache entries to ensure consistency.
     * 
     * @param request the student creation request
     * @return the created student DTO
     * @throws ResourceNotFoundException if department ID is invalid
     */
    @CacheEvict(value = "students", allEntries = true)
    @Transactional
    public StudentDTO create(CreateStudentRequest request) {
        log.info("Creating new student with CIN: {}", request.getCin());
        Student student = studentMapper.toEntity(request);
        Student saved = studentRepository.save(student);
        log.info("Successfully created student with id: {}", saved.getId());
        return studentMapper.toDTO(saved);
    }
    
    /**
     * Updates an existing student.
     * Evicts all student cache entries to ensure consistency.
     * 
     * @param id the student ID to update
     * @param request the updated student data
     * @return the updated student DTO
     * @throws ResourceNotFoundException if student or department not found
     */
    @CacheEvict(value = "students", allEntries = true)
    @Transactional
    public StudentDTO update(Long id, CreateStudentRequest request) {
        log.info("Updating student with id: {}", id);
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        
        // Update fields
        existing.setCin(request.getCin());
        existing.setNom(request.getNom());
        existing.setDateNaissance(request.getDateNaissance());
        existing.setEmail(request.getEmail());
        existing.setAnneePremiereInscription(request.getAnneePremiereInscription());
        
        // Update department if provided
        if (request.getDepartementId() != null) {
            Department department = departmentRepository.findById(request.getDepartementId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.getDepartementId()));
            existing.setDepartement(department);
        } else {
            existing.setDepartement(null);
        }
        
        Student saved = studentRepository.save(existing);
        log.info("Successfully updated student with id: {}", saved.getId());
        return studentMapper.toDTO(saved);
    }
    
    /**
     * Deletes a student by ID.
     * Evicts all student cache entries to ensure consistency.
     * 
     * @param id the student ID to delete
     * @throws ResourceNotFoundException if student not found
     */
    @CacheEvict(value = "students", allEntries = true)
    @Transactional
    public void delete(Long id) {
        log.info("Deleting student with id: {}", id);
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
        log.info("Successfully deleted student with id: {}", id);
    }
}
