package com.example.sm.service.coreservice;

import com.example.sm.dao.FacultyRepository;
import com.example.sm.model.Faculty;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FacultyCoreServiceTest {

    @Mock
    private FacultyRepository repository;
    @InjectMocks
    private FacultyCoreService service;

    @Test
    void save() {

        // given
        Faculty faculty = new Faculty(1, "computer");

        // when
        service.save(faculty);

        // then
        ArgumentCaptor<Faculty> facultyArgumentCaptor =
                ArgumentCaptor.forClass(Faculty.class);

        verify(repository).save(facultyArgumentCaptor.capture());

        Faculty capturedFaculty = facultyArgumentCaptor.getValue();

        assertThat(capturedFaculty).isEqualTo(faculty);
    }

    @Test
    void getAll() {
        service.getAll();
        verify(repository).findAll();
    }

    @Test
    void getAllPaginated() {
        getAll();
    }

    @Test
    @Disabled
    void get() {
    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    @Disabled
    void delete() {
    }
}