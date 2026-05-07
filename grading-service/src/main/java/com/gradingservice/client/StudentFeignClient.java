package com.gradingservice.client;

import com.gradingservice.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign Client for communicating with Etudiant Service.
 * 
 * Uses Eureka service discovery to locate the etudiant-service.
 */
@FeignClient(name = "etudiant-service")
public interface StudentFeignClient {
    
    /**
     * Get student details by ID from Etudiant Service.
     * 
     * @param id the student ID
     * @return the student DTO
     */
    @GetMapping("/api/etudiants/{id}")
    StudentDTO getStudentById(@PathVariable Long id);
}
