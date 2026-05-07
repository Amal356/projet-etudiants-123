package com.studentmanagement.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Global exception handler for the REST API.
 * Provides centralized exception handling and consistent error responses.
 * 
 * Requirements:
 * - 2.2: Provides consistent error handling for API endpoints
 * - 11.1: Global exception handler with @RestControllerAdvice
 * - 11.2: Handles ResourceNotFoundException with HTTP 404
 * - 11.3: Handles MethodArgumentNotValidException with HTTP 400
 * - 11.4: Handles generic exceptions with HTTP 500
 * - 11.5: Error responses include timestamp, status, error, message, and path
 * - 11.6: Logs all exceptions for debugging
 * - 11.7: Does not expose sensitive system information
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * Handles ResourceNotFoundException.
     * Returns HTTP 404 Not Found with structured error response.
     * 
     * @param ex The ResourceNotFoundException that was thrown
     * @param request The web request that caused the exception
     * @return ResponseEntity containing error details with HTTP 404 status
     * 
     * Requirements:
     * - 11.2: Catches ResourceNotFoundException and returns HTTP 404
     * - 11.5: Returns structured JSON with timestamp, status, error, message, path
     * - 11.6: Logs error details
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex,
            WebRequest request) {
        log.error("Resource not found: {}", ex.getMessage());
        
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
            
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    /**
     * Handles MethodArgumentNotValidException for request validation failures.
     * Returns HTTP 400 Bad Request with structured error response.
     * 
     * @param ex The MethodArgumentNotValidException that was thrown
     * @param request The web request that caused the exception
     * @return ResponseEntity containing error details with HTTP 400 status
     * 
     * Requirements:
     * - 11.3: Catches MethodArgumentNotValidException and returns HTTP 400
     * - 11.5: Returns structured JSON with timestamp, status, error, message, path
     * - 11.6: Logs error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        log.error("Validation failed: {}", ex.getMessage());
        
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));
        
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Validation Failed",
            message,
            request.getDescription(false).replace("uri=", "")
        );
            
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    /**
     * Handles all uncaught exceptions in the application.
     * Returns HTTP 500 Internal Server Error with structured error response.
     * 
     * @param ex The exception that was thrown
     * @param request The web request that caused the exception
     * @return ResponseEntity containing error details with HTTP 500 status
     * 
     * Requirements:
     * - 2.2: Returns structured error response for API failures
     * - 11.4: Catches generic exceptions and returns HTTP 500
     * - 11.5: Returns structured JSON with timestamp, status, error, message, path
     * - 11.6: Logs error details with stack trace
     * - 11.7: Does not expose sensitive information to clients
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            WebRequest request) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            "An unexpected error occurred",
            request.getDescription(false).replace("uri=", "")
        );
            
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
