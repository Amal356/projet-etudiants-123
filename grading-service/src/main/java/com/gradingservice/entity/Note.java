package com.gradingservice.entity;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Note entity representing a student grade in the database.
 * 
 * NO Lombok - manual getters, setters, constructors
 */
@Entity
@Table(name = "notes")
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "student_id", nullable = false)
    private Long studentId;
    
    @Column(name = "matiere", nullable = false)
    private String matiere;
    
    @Column(name = "valeur", nullable = false)
    private Double valeur;
    
    // Constructors
    public Note() {
    }
    
    public Note(Long id, Long studentId, String matiere, Double valeur) {
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
        Note note = (Note) o;
        return Objects.equals(id, note.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", matiere='" + matiere + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}
