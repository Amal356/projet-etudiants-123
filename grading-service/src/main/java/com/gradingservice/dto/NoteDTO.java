package com.gradingservice.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Note entity.
 * 
 * NO Lombok - manual getters, setters, constructors
 */
public class NoteDTO {
    
    private Long id;
    private Long studentId;
    private String matiere;
    private Double valeur;
    
    // Constructors
    public NoteDTO() {
    }
    
    public NoteDTO(Long id, Long studentId, String matiere, Double valeur) {
        this.id = id;
        this.studentId = studentId;
        this.matiere = matiere;
        this.valeur = valeur;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
        NoteDTO noteDTO = (NoteDTO) o;
        return Objects.equals(id, noteDTO.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "NoteDTO{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", matiere='" + matiere + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}
