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
        
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setCin(entity.getCin());
        dto.setNom(entity.getNom());
        dto.setDateNaissance(entity.getDateNaissance());
        dto.setEmail(entity.getEmail());
        dto.setAnneePremiereInscription(entity.getAnneePremiereInscription());
        dto.setDepartementId(entity.getDepartement() != null ? entity.getDepartement().getId() : null);
        dto.setDepartementNom(entity.getDepartement() != null ? entity.getDepartement().getNom() : null);
        dto.setAge(entity.age());
        return dto;
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
        
        Student student = new Student();
        student.setCin(dto.getCin());
        student.setNom(dto.getNom());
        student.setDateNaissance(dto.getDateNaissance());
        student.setEmail(dto.getEmail());
        student.setAnneePremiereInscription(dto.getAnneePremiereInscription());
        student.setDepartement(dept);
        return student;
    }
}
