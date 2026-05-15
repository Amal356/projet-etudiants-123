package com.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Department entity representing an academic department in the database.
 * 
 * Requirements:
 * - 7.1: Contains id field of type Long and nom field of type String
 * - 7.1: Has @OneToMany relationship to Student
 * - Q8 Partie 2: Uses Lombok @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
 */
@Entity
@Table(name = "departments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "nom"})
@ToString(exclude = "students")
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nom", nullable = false, unique = true)
    private String nom;
    
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Student> students = new ArrayList<>();
}
