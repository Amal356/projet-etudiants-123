package com.studentmanagement.repository;

import com.studentmanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Department entity.
 * 
 * Requirements:
 * - 7.6: Provides CRUD operations for Department
 * - Custom query method findByNom
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    /**
     * Finds a department by its name.
     * 
     * @param nom the department name
     * @return an Optional containing the department if found
     */
    Optional<Department> findByNom(String nom);
}
