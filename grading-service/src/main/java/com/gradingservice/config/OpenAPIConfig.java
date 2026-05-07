package com.gradingservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI/Swagger configuration for API documentation.
 */
@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI gradingServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Grading Service API")
                        .description("REST API for Student Grading Management")
                        .version("1.0.0"));
    }
}
