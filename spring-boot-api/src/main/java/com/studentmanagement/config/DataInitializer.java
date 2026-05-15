package com.studentmanagement.config;

import com.studentmanagement.entity.Department;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.DepartmentRepository;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;

    public DataInitializer(DepartmentRepository departmentRepository, StudentRepository studentRepository) {
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) {
        if (departmentRepository.count() == 0) {
            // Create departments using Lombok builder
            Department informatique = Department.builder()
                    .nom("Informatique")
                    .build();
            informatique = departmentRepository.save(informatique);

            Department mathematiques = Department.builder()
                    .nom("Mathématiques")
                    .build();
            mathematiques = departmentRepository.save(mathematiques);

            Department physique = Department.builder()
                    .nom("Physique")
                    .build();
            physique = departmentRepository.save(physique);

            if (studentRepository.count() == 0) {
                // Create students using Lombok builder
                Student student1 = Student.builder()
                        .cin("AB123456")
                        .nom("Dupont")
                        .email("jean.dupont@example.com")
                        .dateNaissance(LocalDate.of(2000, 5, 15))
                        .anneePremiereInscription(2022)
                        .departement(informatique)
                        .build();
                studentRepository.save(student1);

                Student student2 = Student.builder()
                        .cin("CD789012")
                        .nom("Martin")
                        .email("marie.martin@example.com")
                        .dateNaissance(LocalDate.of(2001, 8, 22))
                        .anneePremiereInscription(2023)
                        .departement(mathematiques)
                        .build();
                studentRepository.save(student2);

                Student student3 = Student.builder()
                        .cin("EF345678")
                        .nom("Durand")
                        .email("pierre.durand@example.com")
                        .dateNaissance(LocalDate.of(1999, 3, 10))
                        .anneePremiereInscription(2021)
                        .departement(physique)
                        .build();
                studentRepository.save(student3);
            }
        }
    }
}
