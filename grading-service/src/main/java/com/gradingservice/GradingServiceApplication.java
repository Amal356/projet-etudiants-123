package com.gradingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Grading Service Application for managing student grades.
 * 
 * This microservice handles CRUD operations for student grades (notes)
 * and communicates with the Etudiant Service via Feign Client.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
public class GradingServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(GradingServiceApplication.class, args);
    }
}
