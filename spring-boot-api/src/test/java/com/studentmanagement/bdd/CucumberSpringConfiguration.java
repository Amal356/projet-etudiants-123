package com.studentmanagement.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Cucumber Spring configuration class.
 * Integrates Cucumber with Spring Boot test context.
 * 
 * Requirements:
 * - 3.1: Cucumber integration with Spring Boot
 * - 3.2: Test context configuration
 */
@CucumberContextConfiguration
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CucumberSpringConfiguration {
    // This class is intentionally empty.
    // It serves as a bridge between Cucumber and Spring Boot test context.
}
