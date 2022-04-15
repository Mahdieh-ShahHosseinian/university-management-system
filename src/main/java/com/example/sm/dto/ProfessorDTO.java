package com.example.sm.dto;

import com.example.sm.model.Faculty;
import com.example.sm.model.Professor;

import java.util.Objects;

import java.util.Objects;

public class ProfessorDTO extends ApplicationUserDTO<ProfessorDTO> {

    private Integer id;
    private Integer personnelId;
    private Faculty faculty;

    public ProfessorDTO() {

    }

    public ProfessorDTO(Integer id, String username, String password, String firstname, String lastname, Integer nationalId, Integer personnelId, Faculty faculty) {
        super(username, password, firstname, lastname, nationalId);
        this.id = id;
        this.personnelId = personnelId;
        this.faculty = faculty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object obj) {

        ProfessorDTO professorDTO = null;
        if (obj instanceof ProfessorDTO) professorDTO = (ProfessorDTO) obj;
        assert professorDTO != null;
        return Objects.equals(personnelId, professorDTO.getPersonnelId());
    }
}
