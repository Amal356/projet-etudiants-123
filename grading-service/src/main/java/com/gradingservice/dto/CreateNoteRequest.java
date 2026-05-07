package com.gradingservice.dto;

import jakarta.validation.constraints.*;
import java.util.Objects;

/**
 * Request DTO for creating or updating a Note.
 * 
 * NO Lombok - manual getters, setters, constructors
 */
public class CreateNoteRequest {
    
    @NotNull(message = "Student ID is required")
    private Long studentId;
    
    @NotBlank(message = "Matiere is required")
    private String matiere;
    
    @NotNull(message = "Valeur is required")
    @Min(value = 0, message = "Valeur must be at least 0")
    @Max(value = 20, message = "Valeur must be at most 20")
    private Double valeur;
    
    // Constructors
    public CreateNoteRequest() {
    }
    
    public CreateNoteRequest(Long studentId, String matiere, Double valeur) {
        this.studentId = studentId;
        this.matiere = matiere;
        this.valeur = valeur;
    }
    
    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public String getMatiere() {
        return matiere;
    }
    
    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
    
    public Double getValeur() {
        return valeur;
    }
    
    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
    
    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateNoteRequest that = (CreateNoteRequest) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(matiere, that.matiere) &&
                Objects.equals(valeur, that.valeur);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId, matiere, valeur);
    }
    
    @Override
    public String toString() {
        return "CreateNoteRequest{" +
                "studentId=" + studentId +
                ", matiere='" + matiere + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}
