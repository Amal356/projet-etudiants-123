package com.studentmanagement.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Data Transfer Object for creating or updating a Student.
 * Contains validation annotations to ensure data integrity.
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
public class CreateStudentRequest {
    
    @NotBlank(message = "CIN is required")
    private String cin;
    
    @NotBlank(message = "Name is required")
    private String nom;
    
    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate dateNaissance;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    
    @Min(value = 1900, message = "Enrollment year must be after 1900")
    private int anneePremiereInscription;
    
    private Long departementId;
}
