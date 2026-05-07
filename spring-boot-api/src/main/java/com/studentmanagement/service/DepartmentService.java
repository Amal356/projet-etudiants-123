package com.studentmanagement.service;

import com.studentmanagement.dto.DepartmentDTO;
import com.studentmanagement.entity.Department;
import com.studentmanagement.exception.ResourceNotFoundException;
import com.studentmanagement.mapper.DepartmentMapper;
import com.studentmanagement.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for Department entity business logic.
 * Implements CRUD operations with caching and logging.
 * 
 * Requirements:
 * - 8.3: Service layer with business logic
 * - 10.6-10.10: Complete CRUD operations for departments
 * - 13.3, 13.4: Caching with Redis
 */
@Service
public class DepartmentService {
    
    private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);
    
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }
    
    /**
     * Retrieves all departments from the database.
     * Results are cached to improve performance.
     * 
     * @return list of all departments as DTOs
     */
    @Cacheable(value = "departments")
    @Transactional(readOnly = true)
    public List<DepartmentDTO> findAll() {
        log.info("Fetching all departments from database");
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves a single department by ID.
     * Result is cached with the department ID as the cache key.
     * 
     * @param id the department ID
     * @return the department DTO
     * @throws ResourceNotFoundException if department not found
     */
    @Cacheable(value = "departments", key = "#id")
    @Transactional(readOnly = true)
    public DepartmentDTO findById(Long id) {
        log.info("Fetching department with id: {}", id);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        return departmentMapper.toDTO(department);
    }
    
    /**
     * Creates a new department.
     * Evicts all department cache entries to ensure consistency.
     * 
     * @param dto the department creation request
     * @return the created department DTO
     */
    @CacheEvict(value = "departments", allEntries = true)
    @Transactional
    public DepartmentDTO create(DepartmentDTO dto) {
        log.info("Creating new department with name: {}", dto.getNom());
        Department department = departmentMapper.toEntity(dto);
        Department saved = departmentRepository.save(department);
        log.info("Successfully created department with id: {}", saved.getId());
        return departmentMapper.toDTO(saved);
    }
    
    /**
     * Updates an existing department.
     * Evicts all department cache entries to ensure consistency.
     * 
     * @param id the department ID to update
     * @param dto the updated department data
     * @return the updated department DTO
     * @throws ResourceNotFoundException if department not found
     */
    @CacheEvict(value = "departments", allEntries = true)
    @Transactional
    public DepartmentDTO update(Long id, DepartmentDTO dto) {
        log.info("Updating department with id: {}", id);
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        
        // Update fields
        existing.setNom(dto.getNom());
        
        Department saved = departmentRepository.save(existing);
        log.info("Successfully updated department with id: {}", saved.getId());
        return departmentMapper.toDTO(saved);
    }
    
    /**
     * Deletes a department by ID.
     * Evicts all department cache entries to ensure consistency.
     * 
     * @param id the department ID to delete
     * @throws ResourceNotFoundException if department not found
     */
    @CacheEvict(value = "departments", allEntries = true)
    @Transactional
    public void delete(Long id) {
        log.info("Deleting department with id: {}", id);
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
        log.info("Successfully deleted department with id: {}", id);
    }
}
