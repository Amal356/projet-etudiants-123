package com.studentmanagement.mapper;

import com.studentmanagement.dto.CreateStudentRequest;
import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.entity.Department;
import com.studentmanagement.entity.Student;
import com.studentmanagement.exception.ResourceNotFoundException;
import com.studentmanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Component;

/**
 * Mapper component for converting between Student entities and DTOs.
 * Handles the mapping logic and department relationship resolution.
 * 
 * Requirements:
 * - 8.7: Mapper for DTO to Entity conversion
 * - 8.10: Inject DepartmentRepository for relationship resolution
 */
@Component
public class StudentMapper {
    
    private final DepartmentRepository departmentRepository;
    
    public StudentMapper(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    
    /**
     * Converts a Student entity to a StudentDTO.
     * Includes calculated age and department information.
     * 
     * @param entity the Student entity to convert
     * @return the StudentDTO representation
     */
    public StudentDTO toDTO(Student entity) {
        if (entity == null) {
            return null;
        }
        
        return StudentDTO.builder()
                .id(entity.getId())
                .cin(entity.getCin())
                .nom(entity.getNom())
                .dateNaissance(entity.getDateNaissance())
                .email(entity.getEmail())
                .anneePremiereInscription(entity.getAnneePremiereInscription())
                .departementId(entity.getDepartement() != null ? entity.getDepartement().getId() : null)
                .departementNom(entity.getDepartement() != null ? entity.getDepartement().getNom() : null)
                .age(entity.age())
                .build();
    }
    
    /**
     * Converts a CreateStudentRequest DTO to a Student entity.
     * Resolves the department relationship if departementId is provided.
     * 
     * @param dto the CreateStudentRequest DTO to convert
     * @return the Student entity
     * @throws ResourceNotFoundException if the department ID is invalid
     */
    public Student toEntity(CreateStudentRequest dto) {
        if (dto == null) {
            return null;
        }
        
        Department dept = null;
        if (dto.getDepartementId() != null) {
            dept = departmentRepository.findById(dto.getDepartementId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dto.getDepartementId()));
        }
        
        return Student.builder()
                .cin(dto.getCin())
                .nom(dto.getNom())
                .dateNaissance(dto.getDateNaissance())
                .email(dto.getEmail())
                .anneePremiereInscription(dto.getAnneePremiereInscription())
                .departement(dept)
                .build();
    }
}
