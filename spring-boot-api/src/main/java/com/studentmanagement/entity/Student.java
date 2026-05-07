package com.studentmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Student entity representing a student record in the database.
 * 
 * Requirements:
 * - 1.1: Contains id field of type Long
 * - 1.2: Contains cin field of type String
 * - 1.3: Contains nom field of type String
 * - 1.4: Contains dateNaissance field of type LocalDate
 * - 1.5: Auto-generates the id field value
 * - 7.2: Contains email field of type String
 * - 7.3: Contains anneePremiereInscription field of type int
 * - 7.3: Contains departement field with @ManyToOne relationship
 * - 2.1: Has age() method that calculates age from dateNaissance
 */
@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "cin", nullable = false, unique = true)
    private String cin;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "annee_premiere_inscription", nullable = false)
    private int anneePremiereInscription;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departement_id")
    private Department departement;
    
    // Constructors
    public Student() {
    }
    
    public Student(Long id, String cin, String nom, LocalDate dateNaissance, String email, int anneePremiereInscription, Department departement) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.anneePremiereInscription = anneePremiereInscription;
        this.departement = departement;
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
    
    public Department getDepartement() {
        return departement;
    }
    
    public void setDepartement(Department departement) {
        this.departement = departement;
    }
    
    /**
     * Calculates the age of the student based on their birth date.
     * 
     * @return the age in complete years, or 0 if dateNaissance is null
     */
    public int age() {
        if (dateNaissance == null) {
            return 0;
        }
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }
    
    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(cin, student.cin);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, cin);
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", cin='" + cin + '\'' +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", email='" + email + '\'' +
                ", anneePremiereInscription=" + anneePremiereInscription +
                '}';
    }
}
