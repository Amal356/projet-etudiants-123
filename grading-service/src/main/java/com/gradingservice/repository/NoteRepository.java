package com.gradingservice.repository;

import com.gradingservice.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Note entity.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    
    /**
     * Find all notes for a specific student.
     * 
     * @param studentId the student ID
     * @return list of notes for the student
     */
    List<Note> findByStudentId(Long studentId);
}
