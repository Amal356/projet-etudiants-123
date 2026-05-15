package com.studentmanagement.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * Data Transfer Object for Student entity.
 * Used for API responses to decouple the API contract from the domain model.
 * 
 * Requirements:
 * - 8.6: DTO for API communication
 * - 8.7: Contains all student attributes including calculated age
 * - Q8 Partie 2: Uses Lombok @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    
    private Long id;
    private String cin;
    private String nom;
    private LocalDate dateNaissance;
    private String email;
    private int anneePremiereInscription;
    private Long departementId;
    private String departementNom;
    private Integer age;
}
