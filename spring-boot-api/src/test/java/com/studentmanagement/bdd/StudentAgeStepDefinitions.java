package com.studentmanagement.bdd;

import com.studentmanagement.entity.Student;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Step definitions for Student age calculation BDD tests.
 * Implements Gherkin scenarios in French (Given/When/Then).
 * 
 * Requirements:
 * - 3.3: Step definitions for age() method testing
 * - 3.4: BDD testing with Cucumber
 * - 3.5: French language support (Étant donné / Quand / Alors)
 */
public class StudentAgeStepDefinitions {
    
    private Student student;
    private int calculatedAge;
    
    @Etantdonné("un étudiant avec la date de naissance {string}")
    public void unEtudiantAvecLaDateDeNaissance(String dateNaissance) {
        // Parse the date string and create a student
        LocalDate birthDate = LocalDate.parse(dateNaissance);
        student = new Student();
        student.setDateNaissance(birthDate);
        student.setNom("Test Student");
        student.setCin("TEST123");
        
        assertNotNull(student, "Student should be created");
        assertNotNull(student.getDateNaissance(), "Birth date should be set");
    }
    
    @Quand("on calcule son âge")
    public void onCalculeSonAge() {
        // Call the age() method
        calculatedAge = student.age();
    }
    
    @Alors("l'âge retourné doit être {int}")
    public void lAgeRetournéDoitÊtre(int expectedAge) {
        // Verify the calculated age matches the expected age
        assertEquals(expectedAge, calculatedAge, 
            String.format("Expected age to be %d but was %d for birth date %s", 
                expectedAge, calculatedAge, student.getDateNaissance()));
    }
}
