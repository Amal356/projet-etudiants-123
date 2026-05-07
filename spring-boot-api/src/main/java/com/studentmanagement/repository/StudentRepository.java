package com.studentmanagement.repository;

import com.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Student entity data access operations.
 * Extends JpaRepository to inherit standard CRUD operations:
 * - findAll()
 * - findById(Long id)
 * - save(Student student)
 * - deleteById(Long id)
 * 
 * Requirements:
 * - 4.2: Uses Spring Data JPA for database operations
 * - 9.1: Custom query method findByAnneePremiereInscription
 * - Custom query method findByCin
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    /**
     * Finds all students by their first enrollment year.
     * 
     * @param annee the enrollment year
     * @return list of students enrolled in the specified year
     */
    List<Student> findByAnneePremiereInscription(int annee);
    
    /**
     * Finds a student by their CIN (national ID card number).
     * 
     * @param cin the student's CIN
     * @return an Optional containing the student if found
     */
    Optional<Student> findByCin(String cin);
}
