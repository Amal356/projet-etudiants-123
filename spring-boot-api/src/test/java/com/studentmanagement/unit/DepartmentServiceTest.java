package com.studentmanagement.unit;

import com.studentmanagement.dto.DepartmentDTO;
import com.studentmanagement.entity.Department;
import com.studentmanagement.exception.ResourceNotFoundException;
import com.studentmanagement.mapper.DepartmentMapper;
import com.studentmanagement.repository.DepartmentRepository;
import com.studentmanagement.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit tests for DepartmentService.
 * Tests all CRUD operations with mocked dependencies.
 * 
 * Requirements:
 * - Q2 Partie 4: Unit tests for DepartmentService
 * - Tests findAll(), findById(), create(), update(), delete()
 * - Uses Mockito for mocking dependencies
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("DepartmentService Unit Tests")
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentService departmentService;

    private Department department1;
    private Department department2;
    private DepartmentDTO departmentDTO1;
    private DepartmentDTO departmentDTO2;

    @BeforeEach
    void setUp() {
        // Setup test data
        department1 = Department.builder()
                .id(1L)
                .nom("Informatique")
                .build();

        department2 = Department.builder()
                .id(2L)
                .nom("Mathématiques")
                .build();

        departmentDTO1 = DepartmentDTO.builder()
                .id(1L)
                .nom("Informatique")
                .build();

        departmentDTO2 = DepartmentDTO.builder()
                .id(2L)
                .nom("Mathématiques")
                .build();
    }

    @Test
    @DisplayName("findAll() should return all departments")
    void testFindAll_Success() {
        // Given
        List<Department> departments = Arrays.asList(department1, department2);
        when(departmentRepository.findAll()).thenReturn(departments);
        when(departmentMapper.toDTO(department1)).thenReturn(departmentDTO1);
        when(departmentMapper.toDTO(department2)).thenReturn(departmentDTO2);

        // When
        List<DepartmentDTO> result = departmentService.findAll();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNom()).isEqualTo("Informatique");
        assertThat(result.get(1).getNom()).isEqualTo("Mathématiques");
        
        verify(departmentRepository, times(1)).findAll();
        verify(departmentMapper, times(2)).toDTO(any(Department.class));
    }

    @Test
    @DisplayName("findAll() should return empty list when no departments exist")
    void testFindAll_EmptyList() {
        // Given
        when(departmentRepository.findAll()).thenReturn(Arrays.asList());

        // When
        List<DepartmentDTO> result = departmentService.findAll();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
        
        verify(departmentRepository, times(1)).findAll();
        verify(departmentMapper, never()).toDTO(any(Department.class));
    }

    @Test
    @DisplayName("findById() should return department when found")
    void testFindById_Success() {
        // Given
        Long departmentId = 1L;
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department1));
        when(departmentMapper.toDTO(department1)).thenReturn(departmentDTO1);

        // When
        DepartmentDTO result = departmentService.findById(departmentId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Informatique");
        
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentMapper, times(1)).toDTO(department1);
    }

    @Test
    @DisplayName("findById() should throw ResourceNotFoundException when department not found")
    void testFindById_NotFound() {
        // Given
        Long departmentId = 999L;
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> departmentService.findById(departmentId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Department not found with id: 999");
        
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentMapper, never()).toDTO(any(Department.class));
    }

    @Test
    @DisplayName("create() should create and return new department")
    void testCreate_Success() {
        // Given
        DepartmentDTO newDepartmentDTO = DepartmentDTO.builder()
                .nom("Physique")
                .build();
        
        Department newDepartment = Department.builder()
                .nom("Physique")
                .build();
        
        Department savedDepartment = Department.builder()
                .id(3L)
                .nom("Physique")
                .build();
        
        DepartmentDTO savedDepartmentDTO = DepartmentDTO.builder()
                .id(3L)
                .nom("Physique")
                .build();

        when(departmentMapper.toEntity(newDepartmentDTO)).thenReturn(newDepartment);
        when(departmentRepository.save(newDepartment)).thenReturn(savedDepartment);
        when(departmentMapper.toDTO(savedDepartment)).thenReturn(savedDepartmentDTO);

        // When
        DepartmentDTO result = departmentService.create(newDepartmentDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getNom()).isEqualTo("Physique");
        
        verify(departmentMapper, times(1)).toEntity(newDepartmentDTO);
        verify(departmentRepository, times(1)).save(newDepartment);
        verify(departmentMapper, times(1)).toDTO(savedDepartment);
    }

    @Test
    @DisplayName("update() should update and return existing department")
    void testUpdate_Success() {
        // Given
        Long departmentId = 1L;
        DepartmentDTO updateDTO = DepartmentDTO.builder()
                .nom("Informatique et Réseaux")
                .build();
        
        Department existingDepartment = Department.builder()
                .id(1L)
                .nom("Informatique")
                .build();
        
        Department updatedDepartment = Department.builder()
                .id(1L)
                .nom("Informatique et Réseaux")
                .build();
        
        DepartmentDTO updatedDTO = DepartmentDTO.builder()
                .id(1L)
                .nom("Informatique et Réseaux")
                .build();

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(existingDepartment));
        when(departmentRepository.save(any(Department.class))).thenReturn(updatedDepartment);
        when(departmentMapper.toDTO(updatedDepartment)).thenReturn(updatedDTO);

        // When
        DepartmentDTO result = departmentService.update(departmentId, updateDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Informatique et Réseaux");
        
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentRepository, times(1)).save(any(Department.class));
        verify(departmentMapper, times(1)).toDTO(updatedDepartment);
    }

    @Test
    @DisplayName("update() should throw ResourceNotFoundException when department not found")
    void testUpdate_NotFound() {
        // Given
        Long departmentId = 999L;
        DepartmentDTO updateDTO = DepartmentDTO.builder()
                .nom("Updated Name")
                .build();
        
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> departmentService.update(departmentId, updateDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Department not found with id: 999");
        
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    @DisplayName("delete() should delete existing department")
    void testDelete_Success() {
        // Given
        Long departmentId = 1L;
        when(departmentRepository.existsById(departmentId)).thenReturn(true);
        doNothing().when(departmentRepository).deleteById(departmentId);

        // When
        departmentService.delete(departmentId);

        // Then
        verify(departmentRepository, times(1)).existsById(departmentId);
        verify(departmentRepository, times(1)).deleteById(departmentId);
    }

    @Test
    @DisplayName("delete() should throw ResourceNotFoundException when department not found")
    void testDelete_NotFound() {
        // Given
        Long departmentId = 999L;
        when(departmentRepository.existsById(departmentId)).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> departmentService.delete(departmentId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Department not found with id: 999");
        
        verify(departmentRepository, times(1)).existsById(departmentId);
        verify(departmentRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("create() should handle null department name gracefully")
    void testCreate_NullName() {
        // Given
        DepartmentDTO nullNameDTO = DepartmentDTO.builder()
                .nom(null)
                .build();
        
        Department nullNameDepartment = Department.builder()
                .nom(null)
                .build();

        when(departmentMapper.toEntity(nullNameDTO)).thenReturn(nullNameDepartment);
        when(departmentRepository.save(nullNameDepartment)).thenThrow(new IllegalArgumentException("Department name cannot be null"));

        // When & Then
        assertThatThrownBy(() -> departmentService.create(nullNameDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Department name cannot be null");
        
        verify(departmentMapper, times(1)).toEntity(nullNameDTO);
        verify(departmentRepository, times(1)).save(nullNameDepartment);
    }
}
