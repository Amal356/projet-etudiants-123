package com.gradingservice.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for Student data from Etudiant Service.
 * MUST match exactly the StudentDTO from etudiant-service for Feign Client compatibility.
 * 
 * NO Lombok - manual getters, setters, constructors
 */
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
    
    // Constructors
    public StudentDTO() {
    }
    
    public StudentDTO(Long id, String cin, String nom, LocalDate dateNaissance, 
                     String email, int anneePremiereInscription, Long departementId,
                     String departementNom, Integer age) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.anneePremiereInscription = anneePremiereInscription;
        this.departementId = departementId;
        this.departementNom = departementNom;
        this.age = age;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public String getDepartementNom() {
        return departementNom;
    }
    
    public void setDepartementNom(String departementNom) {
        this.departementNom = departementNom;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(cin, that.cin);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, cin);
    }
    
    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", cin='" + cin + '\'' +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", email='" + email + '\'' +
                ", anneePremiereInscription=" + anneePremiereInscription +
                ", departementId=" + departementId +
                ", departementNom='" + departementNom + '\'' +
                ", age=" + age +
                '}';
    }
}
