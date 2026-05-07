package com.gradingservice.bdd;

import com.gradingservice.dto.CreateNoteRequest;
import com.gradingservice.dto.NoteDTO;
import com.gradingservice.entity.Note;
import com.gradingservice.repository.NoteRepository;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step definitions for Note BDD tests.
 */
public class NoteStepDefinitions {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private NoteRepository noteRepository;
    
    private ResponseEntity<?> response;
    private NoteDTO createdNote;
    
    @Etantdonné("que le service de notation est disponible")
    public void queLeServiceDeNotationEstDisponible() {
        // Service is available if Spring context loads
        assertNotNull(restTemplate);
    }
    
    @Etantdonné("qu'il existe des notes dans la base de données")
    public void quilExisteDesNotesDansLaBaseDeDonnees() {
        Note note = new Note();
        note.setStudentId(1L);
        note.setMatiere("Physique");
        note.setValeur(14.0);
        noteRepository.save(note);
    }
    
    @Etantdonné("qu'une note existe avec l'ID {string}")
    public void quUneNoteExisteAvecLID(String id) {
        Note note = new Note();
        note.setId(Long.parseLong(id));
        note.setStudentId(1L);
        note.setMatiere("Chimie");
        note.setValeur(16.0);
        noteRepository.save(note);
    }
    
    @Quand("je crée une note avec studentId {string}, matiere {string} et valeur {string}")
    public void jeCreUneNoteAvecStudentIdMatiereEtValeur(String studentId, String matiere, String valeur) {
        CreateNoteRequest request = new CreateNoteRequest();
        request.setStudentId(Long.parseLong(studentId));
        request.setMatiere(matiere);
        request.setValeur(Double.parseDouble(valeur));
        
        response = restTemplate.postForEntity("/api/notes", request, NoteDTO.class);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            createdNote = (NoteDTO) response.getBody();
        }
    }
    
    @Quand("je récupère toutes les notes")
    public void jeRecupereToutesLesNotes() {
        response = restTemplate.getForEntity("/api/notes", List.class);
    }
    
    @Quand("je récupère la note avec l'ID {string}")
    public void jeRecupereLaNoteAvecLID(String id) {
        response = restTemplate.getForEntity("/api/notes/" + id, NoteDTO.class);
    }
    
    @Quand("je mets à jour la note avec l'ID {string} avec la valeur {string}")
    public void jeMetsAJourLaNoteAvecLIDAvecLaValeur(String id, String valeur) {
        CreateNoteRequest request = new CreateNoteRequest();
        request.setStudentId(1L);
        request.setMatiere("Chimie");
        request.setValeur(Double.parseDouble(valeur));
        
        HttpEntity<CreateNoteRequest> entity = new HttpEntity<>(request);
        response = restTemplate.exchange("/api/notes/" + id, HttpMethod.PUT, entity, NoteDTO.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            createdNote = (NoteDTO) response.getBody();
        }
    }
    
    @Quand("je supprime la note avec l'ID {string}")
    public void jeSupprimeLaNoteAvecLID(String id) {
        response = restTemplate.exchange("/api/notes/" + id, HttpMethod.DELETE, null, Void.class);
    }
    
    @Quand("je crée une note avec une valeur invalide {string}")
    public void jeCreUneNoteAvecUneValeurInvalide(String valeur) {
        CreateNoteRequest request = new CreateNoteRequest();
        request.setStudentId(1L);
        request.setMatiere("Test");
        request.setValeur(Double.parseDouble(valeur));
        
        response = restTemplate.postForEntity("/api/notes", request, String.class);
    }
    
    @Alors("la note est créée avec succès")
    public void laNoteEstCreeeAvecSucces() {
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(createdNote);
    }
    
    @Alors("la note contient la matiere {string}")
    public void laNoteContientLaMatiere(String matiere) {
        assertNotNull(createdNote);
        assertEquals(matiere, createdNote.getMatiere());
    }
    
    @Alors("la note contient la valeur {string}")
    public void laNoteContientLaValeur(String valeur) {
        assertNotNull(createdNote);
        assertEquals(Double.parseDouble(valeur), createdNote.getValeur());
    }
    
    @Alors("je reçois une liste de notes")
    public void jeRecoisUneListeDeNotes() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    
    @Alors("je reçois la note avec l'ID {string}")
    public void jeRecoisLaNoteAvecLID(String id) {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        NoteDTO note = (NoteDTO) response.getBody();
        assertNotNull(note);
        assertEquals(Long.parseLong(id), note.getId());
    }
    
    @Alors("la note est mise à jour avec succès")
    public void laNoteEstMiseAJourAvecSucces() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(createdNote);
    }
    
    @Alors("la note est supprimée avec succès")
    public void laNoteEstSupprimeeAvecSucces() {
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    
    @Alors("je reçois une erreur de validation")
    public void jeRecoisUneErreurDeValidation() {
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
