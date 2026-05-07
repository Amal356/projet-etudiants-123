package com.gradingservice.controller;

import com.gradingservice.dto.CreateNoteRequest;
import com.gradingservice.dto.NoteDTO;
import com.gradingservice.dto.StudentNoteDetailsDTO;
import com.gradingservice.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Note management operations.
 * 
 * NO Lombok - manual implementation
 */
@Tag(name = "Notes", description = "Student grading management endpoints")
@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {
    
    private final NoteService noteService;
    
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    
    /**
     * Get all notes.
     */
    @Operation(summary = "Get all notes", description = "Retrieves all student grades")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved notes")
    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllNotes() {
        return ResponseEntity.ok(noteService.findAll());
    }
    
    /**
     * Get note by ID.
     */
    @Operation(summary = "Get note by ID", description = "Retrieves a single note by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved note")
    @ApiResponse(responseCode = "404", description = "Note not found")
    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.findById(id));
    }
    
    /**
     * Create a new note.
     */
    @Operation(summary = "Create new note", description = "Creates a new student grade")
    @ApiResponse(responseCode = "201", description = "Note successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@Valid @RequestBody CreateNoteRequest request) {
        NoteDTO created = noteService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    /**
     * Update an existing note.
     */
    @Operation(summary = "Update note", description = "Updates an existing note by ID")
    @ApiResponse(responseCode = "200", description = "Note successfully updated")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    @ApiResponse(responseCode = "404", description = "Note not found")
    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody CreateNoteRequest request) {
        NoteDTO updated = noteService.update(id, request);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Delete a note by ID.
     */
    @Operation(summary = "Delete note", description = "Deletes a note by ID")
    @ApiResponse(responseCode = "204", description = "Note successfully deleted")
    @ApiResponse(responseCode = "404", description = "Note not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Get student details with all their notes.
     * Uses Feign client to call Etudiant Service.
     */
    @Operation(summary = "Get student with notes", 
               description = "Retrieves student details with all their grades using Feign client")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved student with notes")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @ApiResponse(responseCode = "503", description = "Etudiant Service unavailable")
    @GetMapping("/etudiant/{studentId}/details")
    public ResponseEntity<StudentNoteDetailsDTO> getStudentWithNotes(@PathVariable Long studentId) {
        return ResponseEntity.ok(noteService.getStudentWithNotes(studentId));
    }
}
