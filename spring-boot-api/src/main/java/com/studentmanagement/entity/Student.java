package com.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

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
 * - Q8 Partie 2: Uses Lombok @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
 */
@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "cin"})
@ToString(exclude = "departement")
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
}
