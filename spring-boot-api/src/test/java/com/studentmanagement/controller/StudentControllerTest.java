package com.studentmanagement.controller;

import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for StudentController.
 * Tests the GET /api/etudiants endpoint.
 * 
 * Validates Requirements:
 * - 2.1: Endpoint exists at /api/etudiants
 * - 2.2: Returns list of all students on HTTP GET
 * - 2.3: Returns data in JSON format
 * - 2.4: Includes all Student entity fields
 * - 2.5: Returns empty array when no students exist
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        // Clean database before each test
        studentRepository.deleteAll();
    }

    /**
     * Test: GET /api/etudiants returns 200 OK status
     * Validates Requirement 2.2: HTTP GET request returns student list
     */
    @Test
    void getAllStudents_ReturnsOkStatus() throws Exception {
        mockMvc.perform(get("/api/etudiants"))
                .andExpect(status().isOk());
    }

    /**
     * Test: GET /api/etudiants returns JSON content type
     * Validates Requirement 2.3: Returns data in JSON format
     */
    @Test
    void getAllStudents_ReturnsJsonContentType() throws Exception {
        mockMvc.perform(get("/api/etudiants"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    /**
     * Test: GET /api/etudiants returns empty array when database is empty
     * Validates Requirement 2.5: Returns empty list when no students exist (not 404)
     */
    @Test
    void getAllStudents_WhenDatabaseEmpty_ReturnsEmptyArray() throws Exception {
        mockMvc.perform(get("/api/etudiants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(jsonPath("$", is(empty())));
    }

    /**
     * Test: GET /api/etudiants returns all students with correct fields
     * Validates Requirements:
     * - 2.2: Returns all students
     * - 2.4: Includes all Student entity fields (id, cin, nom, dateNaissance)
     */
    @Test
    void getAllStudents_WithStudentsInDatabase_ReturnsAllStudents() throws Exception {
        // Given: Two students in database
        Student student1 = new Student(null, "AB123456", "Dupont", LocalDate.of(2000, 5, 15),
            "dupont@example.com", 2018, null);
        studentRepository.save(student1);

        Student student2 = new Student(null, "CD789012", "Martin", LocalDate.of(1999, 8, 22),
            "martin@example.com", 2017, null);
        studentRepository.save(student2);

        // When/Then: GET request returns both students with all fields
        mockMvc.perform(get("/api/etudiants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].cin").value("AB123456"))
                .andExpect(jsonPath("$[0].nom").value("Dupont"))
                .andExpect(jsonPath("$[0].dateNaissance").value("2000-05-15"))
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].cin").value("CD789012"))
                .andExpect(jsonPath("$[1].nom").value("Martin"))
                .andExpect(jsonPath("$[1].dateNaissance").value("1999-08-22"));
    }

    /**
     * Test: GET /api/etudiants returns single student correctly
     * Edge case: Verifies endpoint works with exactly one student
     */
    @Test
    void getAllStudents_WithOneStudent_ReturnsSingleStudentArray() throws Exception {
        // Given: One student in database
        Student student = new Student(null, "EF345678", "Bernard", LocalDate.of(2001, 3, 10),
            "bernard@example.com", 2019, null);
        studentRepository.save(student);

        // When/Then: GET request returns array with one student
        mockMvc.perform(get("/api/etudiants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cin").value("EF345678"))
                .andExpect(jsonPath("$[0].nom").value("Bernard"))
                .andExpect(jsonPath("$[0].dateNaissance").value("2001-03-10"));
    }
}
