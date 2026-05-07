package com.studentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Data Transfer Object for Department entity.
 * Used for API requests and responses.
 * 
 * Requirements:
 * - 8.6: DTO for API communication
 * - 10.11: Validation for POST requests
 */
public class DepartmentDTO {
    
    private Long id;
    
    @NotBlank(message = "Department name is required")
    private String nom;
    
    // Constructors
    public DepartmentDTO() {
    }
    
    public DepartmentDTO(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDTO that = (DepartmentDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nom, that.nom);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }
    
    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
