package com.example.sm.service.crudservice;

import com.example.sm.dto.StudentDTO;
import com.example.sm.model.Faculty;
import com.example.sm.model.Student;
import com.example.sm.service.coreservice.StudentCoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentCRUDServiceTest {

    @InjectMocks
    private StudentCRUDService crudService;
    @Mock
    private StudentCoreService coreService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void save() {

        StudentDTO studentDTO = getStudentDTO();

        given(coreService.save(any(Student.class))).willReturn(new Student());
        crudService.save(studentDTO);

        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(coreService).save(argumentCaptor.capture());
        Student captorStudent = argumentCaptor.getValue();

        assertThat(crudService.toDTO(captorStudent)).isEqualTo(studentDTO);
    }

    @Test
    void getAll() {
        crudService.getAll();
        verify(coreService).getAll();
    }

    @Test
    void getAllPaginated() {
        getAll();
    }

    @Test
    void get() {

        given(coreService.get(any(Integer.class))).willReturn(new Student());
        crudService.get(1);
        verify(coreService).get(1);
    }

    @Test
    void update() {

        StudentDTO studentDTO = getStudentDTO();

        given(coreService.update(any(Student.class), any(Integer.class))).willReturn(new Student());
        crudService.update(studentDTO, studentDTO.getId());

        ArgumentCaptor<Student> argumentCaptor1 = ArgumentCaptor.forClass(Student.class);
        ArgumentCaptor<Integer> argumentCaptor2 = ArgumentCaptor.forClass(Integer.class);
        verify(coreService).update(argumentCaptor1.capture(), argumentCaptor2.capture());
        Student captorStudent = argumentCaptor1.getValue();
        Integer captorId = argumentCaptor2.getValue();

        assertThat(crudService.toDTO(captorStudent)).isEqualTo(studentDTO);
        assertThat(captorId).isEqualTo(studentDTO.getId());
    }

    @Test
    void delete() {

        crudService.delete(1);
        verify(coreService).delete(1);
    }

    @Test
    void toDTO() {

        Student student = getStudent();
        StudentDTO studentDTO = crudService.toDTO(student);
        assertThat(studentDTO.getId()).isEqualTo(student.getId());
        assertThat(studentDTO.getUsername()).isEqualTo(student.getUsername());
        assertThat(studentDTO.getFirstname()).isEqualTo(student.getFirstname());
        assertThat(studentDTO.getLastname()).isEqualTo(student.getLastname());
        assertThat(studentDTO.getNationalId()).isEqualTo(student.getNationalId());
        assertThat(studentDTO.getStudentId()).isEqualTo(student.getStudentId());
    }

    @Test
    void fromDTO() {

        StudentDTO studentDTO = getStudentDTO();
        Student student = crudService.fromDTO(studentDTO);
        assertThat(student.getId()).isEqualTo(studentDTO.getId());
        assertThat(student.getUsername()).isEqualTo(studentDTO.getUsername());
        assertThat(student.getFirstname()).isEqualTo(studentDTO.getFirstname());
        assertThat(student.getLastname()).isEqualTo(studentDTO.getLastname());
        assertThat(student.getNationalId()).isEqualTo(studentDTO.getNationalId());
        assertThat(student.getStudentId()).isEqualTo(studentDTO.getStudentId());
    }

    private Student getStudent() {
        return new Student(
                5, "lindi", "lin34", "Mahdieh", "Shahi",
                226, 98200, new Faculty(2, "com"));
    }

    private StudentDTO getStudentDTO() {
        return new StudentDTO(
                5, "lindi", "lin34", "Mahdieh", "Shahi",
                226, 98200, new Faculty(2, "com"));
    }
}