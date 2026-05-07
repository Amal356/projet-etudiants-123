package com.studentmanagement.exception;

/**
 * Exception thrown when a requested resource is not found.
 * Handled by GlobalExceptionHandler to return HTTP 404 responses.
 * 
 * Requirements:
 * - 10.12: Custom exception for resource not found scenarios
 * - 11.2: Exception handled by global exception handler
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new ResourceNotFoundException with the specified message.
     * 
     * @param message the detail message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
