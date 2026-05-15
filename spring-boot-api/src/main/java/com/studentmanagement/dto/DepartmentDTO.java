package com.studentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Data Transfer Object for Department entity.
 * Used for API requests and responses.
 * 
 * Requirements:
 * - 8.6: DTO for API communication
 * - 10.11: Validation for POST requests
 * - Q8 Partie 2: Uses Lombok @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    
    private Long id;
    
    @NotBlank(message = "Department name is required")
    private String nom;
}
