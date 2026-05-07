package com.studentmanagement.mapper;

import com.studentmanagement.dto.DepartmentDTO;
import com.studentmanagement.entity.Department;
import org.springframework.stereotype.Component;

/**
 * Mapper component for converting between Department entities and DTOs.
 * 
 * Requirements:
 * - 8.7: Mapper for DTO to Entity conversion
 */
@Component
public class DepartmentMapper {
    
    /**
     * Converts a Department entity to a DepartmentDTO.
     * 
     * @param entity the Department entity to convert
     * @return the DepartmentDTO representation
     */
    public DepartmentDTO toDTO(Department entity) {
        if (entity == null) {
            return null;
        }
        
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        return dto;
    }
    
    /**
     * Converts a DepartmentDTO to a Department entity.
     * 
     * @param dto the DepartmentDTO to convert
     * @return the Department entity
     */
    public Department toEntity(DepartmentDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Department entity = new Department();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        return entity;
    }
}
