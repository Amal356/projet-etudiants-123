package com.studentmanagement.unit;

import com.studentmanagement.dto.CreateStudentRequest;
import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.entity.Department;
import com.studentmanagement.entity.Student;
import com.studentmanagement.exception.ResourceNotFoundException;
import com.studentmanagement.mapper.StudentMapper;
import com.studentmanagement.repository.DepartmentRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit tests for StudentService.
 * Uses Mockito to isolate the service layer from dependencies.
 * 
 * PARTIE 4 - Q2: Tests unitaires avec JUnit 5 + Mockito
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("StudentService Unit Tests")
class StudentServiceTest {
    
    @Mock
    private StudentRepository studentRepository;
    
    @Mock
    private DepartmentRepository departmentRepository;
    
    @Mock
    private StudentMapper studentMapper;
    
    @InjectMocks
    private StudentService studentService;
    
    private Student student;
    private StudentDTO studentDTO;
    private CreateStudentRequest createRequest;
    private Department department;
    
    @BeforeEach
    void setUp() {
        // Setup test data
        department = new Department();
        department.setId(1L);
        department.setNom("Informatique");
        
        student = new Student();
        student.setId(1L);
        student.setCin("12345678");
        student.setNom("Dupont");
        student.setDateNaissance(LocalDate.of(2000, 1, 1));
        student.setEmail("dupont@example.com");
        student.setAnneePremiereInscription(2020);
        student.setDepartement(department);
        
        studentDTO = new StudentDTO();
        studentDTO.setId(1L);
        studentDTO.setCin("12345678");
        studentDTO.setNom("Dupont");
        studentDTO.setDateNaissance(LocalDate.of(2000, 1, 1));
        studentDTO.setEmail("dupont@example.com");
        studentDTO.setAnneePremiereInscription(2020);
        studentDTO.setAge(24);
        
        createRequest = new CreateStudentRequest();
        createRequest.setCin("12345678");
        createRequest.setNom("Dupont");
        createRequest.setDateNaissance(LocalDate.of(2000, 1, 1));
        createRequest.setEmail("dupont@example.com");
        createRequest.setAnneePremiereInscription(2020);
        createRequest.setDepartementId(1L);
    }
    
    @Test
    @DisplayName("Should return all students")
    void shouldReturnAllStudents() {
        // given
        List<Student> students = Arrays.asList(student);
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toDTO(any(Student.class))).thenReturn(studentDTO);
        
        // when
        List<StudentDTO> result = studentService.findAll();
        
        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNom()).isEqualTo("Dupont");
        verify(studentRepository, times(1)).findAll();
        verify(studentMapper, times(1)).toDTO(any(Student.class));
    }
    
    @Test
    @DisplayName("Should return student by ID")
    void shouldReturnStudentById() {
        // given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);
        
        // when
        StudentDTO result = studentService.findById(1L);
        
        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Dupont");
        verify(studentRepository, times(1)).findById(1L);
    }
    
    @Test
    @DisplayName("Should throw exception when student not found by ID")
    void shouldThrowExceptionWhenStudentNotFoundById() {
        // given
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        // when & then
        assertThatThrownBy(() -> studentService.findById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Student not found with id: 999");
        
        verify(studentRepository, times(1)).findById(999L);
        verify(studentMapper, never()).toDTO(any());
    }
    
    @Test
    @DisplayName("Should return students by enrollment year")
    void shouldReturnStudentsByEnrollmentYear() {
        // given
        List<Student> students = Arrays.asList(student);
        when(studentRepository.findByAnneePremiereInscription(2020)).thenReturn(students);
        when(studentMapper.toDTO(any(Student.class))).thenReturn(studentDTO);
        
        // when
        List<StudentDTO> result = studentService.findByAnneeInscription(2020);
        
        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAnneePremiereInscription()).isEqualTo(2020);
        verify(studentRepository, times(1)).findByAnneePremiereInscription(2020);
    }
    
    @Test
    @DisplayName("Should create new student successfully")
    void shouldCreateNewStudent() {
        // given
        when(studentMapper.toEntity(createRequest)).thenReturn(student);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);
        
        // when
        StudentDTO result = studentService.create(createRequest);
        
        // then
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("Dupont");
        assertThat(result.getCin()).isEqualTo("12345678");
        verify(studentRepository, times(1)).save(any(Student.class));
        verify(studentMapper, times(1)).toEntity(createRequest);
        verify(studentMapper, times(1)).toDTO(student);
    }
    
    @Test
    @DisplayName("Should update existing student successfully")
    void shouldUpdateExistingStudent() {
        // given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);
        
        // when
        StudentDTO result = studentService.update(1L, createRequest);
        
        // then
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("Dupont");
        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).save(any(Student.class));
    }
    
    @Test
    @DisplayName("Should throw exception when updating non-existent student")
    void shouldThrowExceptionWhenUpdatingNonExistentStudent() {
        // given
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        // when & then
        assertThatThrownBy(() -> studentService.update(999L, createRequest))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Student not found with id: 999");
        
        verify(studentRepository, times(1)).findById(999L);
        verify(studentRepository, never()).save(any());
    }
    
    @Test
    @DisplayName("Should throw exception when updating with invalid department")
    void shouldThrowExceptionWhenUpdatingWithInvalidDepartment() {
        // given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        // when & then
        assertThatThrownBy(() -> studentService.update(1L, createRequest))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Department not found");
        
        verify(studentRepository, times(1)).findById(1L);
        verify(departmentRepository, times(1)).findById(1L);
        verify(studentRepository, never()).save(any());
    }
    
    @Test
    @DisplayName("Should delete student successfully")
    void shouldDeleteStudent() {
        // given
        when(studentRepository.existsById(1L)).thenReturn(true);
        doNothing().when(studentRepository).deleteById(1L);
        
        // when
        studentService.delete(1L);
        
        // then
        verify(studentRepository, times(1)).existsById(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }
    
    @Test
    @DisplayName("Should throw exception when deleting non-existent student")
    void shouldThrowExceptionWhenDeletingNonExistentStudent() {
        // given
        when(studentRepository.existsById(anyLong())).thenReturn(false);
        
        // when & then
        assertThatThrownBy(() -> studentService.delete(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Student not found with id: 999");
        
        verify(studentRepository, times(1)).existsById(999L);
        verify(studentRepository, never()).deleteById(anyLong());
    }
    
    @Test
    @DisplayName("Should update student with null department")
    void shouldUpdateStudentWithNullDepartment() {
        // given
        createRequest.setDepartementId(null);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);
        
        // when
        StudentDTO result = studentService.update(1L, createRequest);
        
        // then
        assertThat(result).isNotNull();
        verify(studentRepository, times(1)).findById(1L);
        verify(departmentRepository, never()).findById(anyLong());
        verify(studentRepository, times(1)).save(any(Student.class));
    }
}
