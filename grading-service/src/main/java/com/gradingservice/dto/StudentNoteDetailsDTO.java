package com.gradingservice.dto;

import java.util.List;
import java.util.Objects;

/**
 * DTO combining student information with their notes.
 * 
 * NO Lombok - manual getters, setters, constructors
 */
public class StudentNoteDetailsDTO {
    
    private StudentDTO student;
    private List<NoteDTO> notes;
    
    // Constructors
    public StudentNoteDetailsDTO() {
    }
    
    public StudentNoteDetailsDTO(StudentDTO student, List<NoteDTO> notes) {
        this.student = student;
        this.notes = notes;
    }
    
    // Getters and Setters
    public StudentDTO getStudent() {
        return student;
    }
    
    public void setStudent(StudentDTO student) {
        this.student = student;
    }
    
    public List<NoteDTO> getNotes() {
        return notes;
    }
    
    public void setNotes(List<NoteDTO> notes) {
        this.notes = notes;
    }
    
    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentNoteDetailsDTO that = (StudentNoteDetailsDTO) o;
        return Objects.equals(student, that.student);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(student);
    }
    
    @Override
    public String toString() {
        return "StudentNoteDetailsDTO{" +
                "student=" + student +
                ", notes=" + notes +
                '}';
    }
}
