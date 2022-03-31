package com.example.sm.dto;

import com.example.sm.model.Faculty;

public class ProfessorDTO extends ApplicationUserDTO<ProfessorDTO> {

    private Integer id;
    private Integer personnelId;
    private Faculty faculty;

    public ProfessorDTO() {

    }

    public ProfessorDTO(Integer id, String username, String password, String firstName, String lastName, Integer nationalId, Integer personnelId, Faculty faculty) {
        super(username, password, firstName, lastName, nationalId);
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
}
