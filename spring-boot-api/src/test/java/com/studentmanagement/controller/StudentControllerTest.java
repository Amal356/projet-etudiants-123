package com.studentmanagement.controller;

import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "spring.cloud.discovery.enabled=false",
    "eureka.client.enabled=false",
    "spring.cache.type=none"
})
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        // Setup default behavior for mocks
    }

    /**
     * Test: GET /api/etudiants returns 200 OK status
     * Validates Requirement 2.2: HTTP GET request returns student list
     */
    @Test
    void getAllStudents_ReturnsOkStatus() throws Exception {
        when(studentService.findAll()).thenReturn(Collections.emptyList());
        
        mockMvc.perform(get("/api/etudiants"))
                .andExpect(status().isOk());
    }

    /**
     * Test: GET /api/etudiants returns JSON content type
     * Validates Requirement 2.3: Returns data in JSON format
     */
    @Test
    void getAllStudents_ReturnsJsonContentType() throws Exception {
        when(studentService.findAll()).thenReturn(Collections.emptyList());
        
        mockMvc.perform(get("/api/etudiants"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    /**
     * Test: GET /api/etudiants returns empty array when database is empty
     * Validates Requirement 2.5: Returns empty list when no students exist (not 404)
     */
    @Test
    void getAllStudents_WhenDatabaseEmpty_ReturnsEmptyArray() throws Exception {
        when(studentService.findAll()).thenReturn(Collections.emptyList());
        
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
        // Given: Two students as DTOs
        StudentDTO student1 = new StudentDTO(1L, "AB123456", "Dupont", LocalDate.of(2000, 5, 15),
            "dupont@example.com", 2018, null, null, 26);
        StudentDTO student2 = new StudentDTO(2L, "CD789012", "Martin", LocalDate.of(1999, 8, 22),
            "martin@example.com", 2017, null, null, 26);
        
        when(studentService.findAll()).thenReturn(Arrays.asList(student1, student2));

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
        // Given: One student as DTO
        StudentDTO student = new StudentDTO(1L, "EF345678", "Bernard", LocalDate.of(2001, 3, 10),
            "bernard@example.com", 2019, null, null, 25);
        
        when(studentService.findAll()).thenReturn(Collections.singletonList(student));

        // When/Then: GET request returns array with one student
        mockMvc.perform(get("/api/etudiants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cin").value("EF345678"))
                .andExpect(jsonPath("$[0].nom").value("Bernard"))
                .andExpect(jsonPath("$[0].dateNaissance").value("2001-03-10"));
    }
}
