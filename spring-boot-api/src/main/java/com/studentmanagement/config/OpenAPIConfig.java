package com.studentmanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI/Swagger configuration for the Student Management System.
 * Provides API documentation and interactive testing interface.
 */
@Configuration
public class OpenAPIConfig {
    
    /**
     * Configures custom OpenAPI documentation with API metadata.
     * 
     * @return configured OpenAPI instance
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management API")
                        .version("2.0")
                        .description("REST API for managing students and departments with caching and comprehensive documentation")
                        .contact(new Contact()
                                .name("Student Management Team")
                                .email("support@studentmanagement.com")));
    }
}
