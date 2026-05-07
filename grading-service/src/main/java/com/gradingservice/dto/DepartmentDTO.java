package com.gradingservice.dto;

import java.util.Objects;

/**
 * DTO for Department data from Etudiant Service.
 * 
 * NO Lombok - manual getters, setters, constructors
 */
public class DepartmentDTO {
    
    private Long id;
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
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
