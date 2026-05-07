package com.studentmanagement.integration;

import com.studentmanagement.entity.Department;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.DepartmentRepository;
import com.studentmanagement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for Student entity with real PostgreSQL database.
 * Uses Testcontainers to spin up a PostgreSQL container for testing.
 * 
 * PARTIE 4 - Q2 & Q3: Tests d'intégration avec Testcontainers
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@DisplayName("Student Integration Tests with Testcontainers")
class StudentIntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        
        // Disable Redis for integration tests
        registry.add("spring.cache.type", () -> "none");
        registry.add("spring.data.redis.host", () -> "localhost");
        registry.add("spring.data.redis.port", () -> "6379");
        
        // Disable Eureka for integration tests
        registry.add("eureka.client.enabled", () -> "false");
    }
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    private Department department;
    
    @BeforeEach
    void setUp() {
        // Clean database before each test
        studentRepository.deleteAll();
        departmentRepository.deleteAll();
        
        // Create test department
        department = new Department();
        department.setNom("Informatique");
        department = departmentRepository.save(department);
    }
    
    @Test
    @DisplayName("Should persist and retrieve student")
    void shouldPersistAndRetrieveStudent() {
        // given
        Student student = new Student();
        student.setCin("12345678");
        student.setNom("Dupont");
        student.setDateNaissance(LocalDate.of(2000, 1, 1));
        student.setEmail("dupont@example.com");
        student.setAnneePremiereInscription(2020);
        student.setDepartement(department);
        
        // when
        Student saved = studentRepository.save(student);
        
        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getNom()).isEqualTo("Dupont");
        assertThat(saved.getCin()).isEqualTo("12345678");
        assertThat(saved.getDepartement()).isNotNull();
        assertThat(saved.getDepartement().getNom()).isEqualTo("Informatique");
        
        // Verify retrieval
        Optional<Student> retrieved = studentRepository.findById(saved.getId());
        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getNom()).isEqualTo("Dupont");
    }
    
    @Test
    @DisplayName("Should find all students")
    void shouldFindAllStudents() {
        // given
        Student student1 = createStudent("11111111", "Alice", "alice@example.com", 2020);
        Student student2 = createStudent("22222222", "Bob", "bob@example.com", 2021);
        studentRepository.save(student1);
        studentRepository.save(student2);
        
        // when
        List<Student> students = studentRepository.findAll();
        
        // then
        assertThat(students).hasSize(2);
        assertThat(students).extracting(Student::getNom)
                .containsExactlyInAnyOrder("Alice", "Bob");
    }
    
    @Test
    @DisplayName("Should find students by enrollment year")
    void shouldFindStudentsByEnrollmentYear() {
        // given
        Student student1 = createStudent("11111111", "Alice", "alice@example.com", 2020);
        Student student2 = createStudent("22222222", "Bob", "bob@example.com", 2020);
        Student student3 = createStudent("33333333", "Charlie", "charlie@example.com", 2021);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        
        // when
        List<Student> students2020 = studentRepository.findByAnneePremiereInscription(2020);
        List<Student> students2021 = studentRepository.findByAnneePremiereInscription(2021);
        
        // then
        assertThat(students2020).hasSize(2);
        assertThat(students2020).extracting(Student::getNom)
                .containsExactlyInAnyOrder("Alice", "Bob");
        
        assertThat(students2021).hasSize(1);
        assertThat(students2021.get(0).getNom()).isEqualTo("Charlie");
    }
    
    @Test
    @DisplayName("Should update student")
    void shouldUpdateStudent() {
        // given
        Student student = createStudent("12345678", "Dupont", "dupont@example.com", 2020);
        Student saved = studentRepository.save(student);
        
        // when
        saved.setNom("Dupont-Martin");
        saved.setEmail("dupont.martin@example.com");
        Student updated = studentRepository.save(saved);
        
        // then
        assertThat(updated.getId()).isEqualTo(saved.getId());
        assertThat(updated.getNom()).isEqualTo("Dupont-Martin");
        assertThat(updated.getEmail()).isEqualTo("dupont.martin@example.com");
        
        // Verify in database
        Optional<Student> retrieved = studentRepository.findById(updated.getId());
        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getNom()).isEqualTo("Dupont-Martin");
    }
    
    @Test
    @DisplayName("Should delete student")
    void shouldDeleteStudent() {
        // given
        Student student = createStudent("12345678", "Dupont", "dupont@example.com", 2020);
        Student saved = studentRepository.save(student);
        Long studentId = saved.getId();
        
        // when
        studentRepository.deleteById(studentId);
        
        // then
        Optional<Student> retrieved = studentRepository.findById(studentId);
        assertThat(retrieved).isEmpty();
    }
    
    @Test
    @DisplayName("Should handle student without department")
    void shouldHandleStudentWithoutDepartment() {
        // given
        Student student = new Student();
        student.setCin("12345678");
        student.setNom("Dupont");
        student.setDateNaissance(LocalDate.of(2000, 1, 1));
        student.setEmail("dupont@example.com");
        student.setAnneePremiereInscription(2020);
        student.setDepartement(null);
        
        // when
        Student saved = studentRepository.save(student);
        
        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getDepartement()).isNull();
        
        // Verify retrieval
        Optional<Student> retrieved = studentRepository.findById(saved.getId());
        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getDepartement()).isNull();
    }
    
    @Test
    @DisplayName("Should maintain relationship with department")
    void shouldMaintainRelationshipWithDepartment() {
        // given
        Student student1 = createStudent("11111111", "Alice", "alice@example.com", 2020);
        Student student2 = createStudent("22222222", "Bob", "bob@example.com", 2021);
        Student saved1 = studentRepository.save(student1);
        Student saved2 = studentRepository.save(student2);
        
        // when - verify department IDs are set
        assertThat(saved1.getDepartement()).isNotNull();
        assertThat(saved2.getDepartement()).isNotNull();
        assertThat(saved1.getDepartement().getId()).isEqualTo(department.getId());
        assertThat(saved2.getDepartement().getId()).isEqualTo(department.getId());
        
        // then - verify department exists in database
        Optional<Department> retrievedDept = departmentRepository.findById(department.getId());
        assertThat(retrievedDept).isPresent();
        assertThat(retrievedDept.get().getNom()).isEqualTo("Informatique");
    }
    
    @Test
    @DisplayName("Should calculate age correctly")
    void shouldCalculateAgeCorrectly() {
        // given
        Student student = new Student();
        student.setCin("12345678");
        student.setNom("Dupont");
        student.setDateNaissance(LocalDate.of(2000, 1, 1));
        student.setEmail("dupont@example.com");
        student.setAnneePremiereInscription(2020);
        student.setDepartement(department);
        
        // when
        Student saved = studentRepository.save(student);
        
        // then
        assertThat(saved.age()).isGreaterThan(20);
        assertThat(saved.age()).isLessThan(30);
    }
    
    @Test
    @DisplayName("Should verify PostgreSQL container is running")
    void shouldVerifyPostgreSQLContainerIsRunning() {
        assertThat(postgres.isRunning()).isTrue();
        assertThat(postgres.getDatabaseName()).isEqualTo("testdb");
        assertThat(postgres.getUsername()).isEqualTo("test");
    }
    
    // Helper method to create a student
    private Student createStudent(String cin, String nom, String email, int annee) {
        Student student = new Student();
        student.setCin(cin);
        student.setNom(nom);
        student.setDateNaissance(LocalDate.of(2000, 1, 1));
        student.setEmail(email);
        student.setAnneePremiereInscription(annee);
        student.setDepartement(department);
        return student;
    }
}
