package com.gradingservice.mapper;

import com.gradingservice.dto.CreateNoteRequest;
import com.gradingservice.dto.NoteDTO;
import com.gradingservice.entity.Note;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Note entity and DTOs.
 * 
 * NO Lombok - manual implementation
 */
@Component
public class NoteMapper {
    
    /**
     * Convert Note entity to NoteDTO.
     */
    public NoteDTO toDTO(Note note) {
        if (note == null) {
            return null;
        }
        return new NoteDTO(
                note.getId(),
                note.getStudentId(),
                note.getMatiere(),
                note.getValeur()
        );
    }
    
    /**
     * Convert CreateNoteRequest to Note entity.
     */
    public Note toEntity(CreateNoteRequest request) {
        if (request == null) {
            return null;
        }
        Note note = new Note();
        note.setStudentId(request.getStudentId());
        note.setMatiere(request.getMatiere());
        note.setValeur(request.getValeur());
        return note;
    }
}
