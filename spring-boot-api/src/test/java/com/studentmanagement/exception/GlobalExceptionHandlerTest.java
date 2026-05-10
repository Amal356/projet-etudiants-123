package com.studentmanagement.exception;

import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for GlobalExceptionHandler.
 * Validates that exceptions are properly handled and return structured error responses.
 * 
 * Requirements:
 * - 2.2: Verifies consistent error handling for API endpoints
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "spring.cloud.discovery.enabled=false",
    "eureka.client.enabled=false",
    "spring.cache.type=none"
})
class GlobalExceptionHandlerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private StudentRepository studentRepository;
    
    @BeforeEach
    void setUp() {
        // Reset mocks before each test
    }
    
    /**
     * Test that when an exception is thrown, the GlobalExceptionHandler
     * returns a proper error response with 500 status.
     * 
     * Requirements:
     * - 2.2: Returns structured error response for API failures
     */
    @Test
    void handleException_ReturnsInternalServerError() throws Exception {
        // Arrange: Mock repository to throw an exception
        when(studentRepository.findAll()).thenThrow(new RuntimeException("Database connection failed"));
        
        // Act & Assert: Verify error response structure
        mockMvc.perform(get("/api/etudiants"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.timestamp").value(notNullValue()))
            .andExpect(jsonPath("$.status").value(500))
            .andExpect(jsonPath("$.error").value("Internal Server Error"))
            .andExpect(jsonPath("$.message").value("An unexpected error occurred"))
            .andExpect(jsonPath("$.path").value("/api/etudiants"));
    }
    
    /**
     * Test that the error response includes all required fields.
     * 
     * Requirements:
     * - 2.2: Error response includes timestamp, status, error, message, and path
     */
    @Test
    void handleException_IncludesAllRequiredFields() throws Exception {
        // Arrange: Mock repository to throw an exception
        when(studentRepository.findAll()).thenThrow(new RuntimeException("Test exception"));
        
        // Act & Assert: Verify all fields are present
        mockMvc.perform(get("/api/etudiants"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.status").exists())
            .andExpect(jsonPath("$.error").exists())
            .andExpect(jsonPath("$.message").exists())
            .andExpect(jsonPath("$.path").exists());
    }
}
