package com.studentmanagement.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Data Transfer Object for creating or updating a Student.
 * Contains validation annotations to ensure data integrity.
 * 
 * Requirements:
 * - 8.6: DTO for API communication
 * - 10.11: Validation for POST requests
 */
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
    
    // Constructors
    public CreateStudentRequest() {
    }
    
    public CreateStudentRequest(String cin, String nom, LocalDate dateNaissance, String email, 
                               int anneePremiereInscription, Long departementId) {
        this.cin = cin;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.anneePremiereInscription = anneePremiereInscription;
        this.departementId = departementId;
    }
    
    // Getters and Setters
    public String getCin() {
        return cin;
    }
    
    public void setCin(String cin) {
        this.cin = cin;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getAnneePremiereInscription() {
        return anneePremiereInscription;
    }
    
    public void setAnneePremiereInscription(int anneePremiereInscription) {
        this.anneePremiereInscription = anneePremiereInscription;
    }
    
    public Long getDepartementId() {
        return departementId;
    }
    
    public void setDepartementId(Long departementId) {
        this.departementId = departementId;
    }
    
    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateStudentRequest that = (CreateStudentRequest) o;
        return Objects.equals(cin, that.cin);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(cin);
    }
    
    @Override
    public String toString() {
        return "CreateStudentRequest{" +
                "cin='" + cin + '\'' +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", email='" + email + '\'' +
                ", anneePremiereInscription=" + anneePremiereInscription +
                ", departementId=" + departementId +
                '}';
    }
}
