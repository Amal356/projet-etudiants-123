package com.studentmanagement.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Department entity representing an academic department in the database.
 * 
 * Requirements:
 * - 7.1: Contains id field of type Long and nom field of type String
 * - 7.1: Has @OneToMany relationship to Student
 */
@Entity
@Table(name = "departments")
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nom", nullable = false, unique = true)
    private String nom;
    
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();
    
    // Constructors
    public Department() {
    }
    
    public Department(Long id, String nom) {
        this.id = id;
        this.nom = nom;
        this.students = new ArrayList<>();
    }
    
    public Department(Long id, String nom, List<Student> students) {
        this.id = id;
        this.nom = nom;
        this.students = students != null ? students : new ArrayList<>();
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
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(nom, that.nom);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }
    
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
