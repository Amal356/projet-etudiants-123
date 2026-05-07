package com.gradingservice.service;

import com.gradingservice.client.StudentFeignClient;
import com.gradingservice.dto.CreateNoteRequest;
import com.gradingservice.dto.NoteDTO;
import com.gradingservice.dto.StudentDTO;
import com.gradingservice.dto.StudentNoteDetailsDTO;
import com.gradingservice.entity.Note;
import com.gradingservice.exception.ResourceNotFoundException;
import com.gradingservice.mapper.NoteMapper;
import com.gradingservice.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for Note entity business logic.
 * 
 * NO Lombok - manual implementation
 */
@Service
public class NoteService {
    
    private static final Logger log = LoggerFactory.getLogger(NoteService.class);
    
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final StudentFeignClient studentFeignClient;
    
    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper, 
                      StudentFeignClient studentFeignClient) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
        this.studentFeignClient = studentFeignClient;
    }
    
    /**
     * Retrieve all notes.
     */
    @Cacheable(value = "notes")
    @Transactional(readOnly = true)
    public List<NoteDTO> findAll() {
        log.info("Fetching all notes from database");
        return noteRepository.findAll().stream()
                .map(noteMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieve a note by ID.
     */
    @Cacheable(value = "notes", key = "#id")
    @Transactional(readOnly = true)
    public NoteDTO findById(Long id) {
        log.info("Fetching note with id: {}", id);
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id: " + id));
        return noteMapper.toDTO(note);
    }
    
    /**
     * Retrieve all notes for a specific student.
     */
    @Cacheable(value = "notes", key = "'student-' + #studentId")
    @Transactional(readOnly = true)
    public List<NoteDTO> findByStudentId(Long studentId) {
        log.info("Fetching notes for student id: {}", studentId);
        return noteRepository.findByStudentId(studentId).stream()
                .map(noteMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Create a new note.
     */
    @CacheEvict(value = "notes", allEntries = true)
    @Transactional
    public NoteDTO create(CreateNoteRequest request) {
        log.info("Creating new note for student id: {}", request.getStudentId());
        Note note = noteMapper.toEntity(request);
        Note saved = noteRepository.save(note);
        log.info("Successfully created note with id: {}", saved.getId());
        return noteMapper.toDTO(saved);
    }
    
    /**
     * Update an existing note.
     */
    @CacheEvict(value = "notes", allEntries = true)
    @Transactional
    public NoteDTO update(Long id, CreateNoteRequest request) {
        log.info("Updating note with id: {}", id);
        Note existing = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id: " + id));
        
        existing.setStudentId(request.getStudentId());
        existing.setMatiere(request.getMatiere());
        existing.setValeur(request.getValeur());
        
        Note saved = noteRepository.save(existing);
        log.info("Successfully updated note with id: {}", saved.getId());
        return noteMapper.toDTO(saved);
    }
    
    /**
     * Delete a note by ID.
     */
    @CacheEvict(value = "notes", allEntries = true)
    @Transactional
    public void delete(Long id) {
        log.info("Deleting note with id: {}", id);
        if (!noteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Note not found with id: " + id);
        }
        noteRepository.deleteById(id);
        log.info("Successfully deleted note with id: {}", id);
    }
    
    /**
     * Get student details with all their notes.
     * Uses Feign client to call Etudiant Service.
     */
    @Transactional(readOnly = true)
    public StudentNoteDetailsDTO getStudentWithNotes(Long studentId) {
        log.info("Fetching student details with notes for student id: {}", studentId);
        
        try {
            // Call Etudiant Service via Feign Client
            StudentDTO student = studentFeignClient.getStudentById(studentId);
            
            // Get all notes for the student
            List<NoteDTO> notes = findByStudentId(studentId);
            
            return new StudentNoteDetailsDTO(student, notes);
        } catch (Exception e) {
            log.error("Error fetching student details: {}", e.getMessage());
            throw new RuntimeException("Unable to fetch student details from Etudiant Service", e);
        }
    }
}
