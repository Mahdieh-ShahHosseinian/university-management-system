package com.example.sm.dto;

import com.example.sm.model.Faculty;

import java.util.Objects;

public class StudentDTO extends ApplicationUserDTO<StudentDTO> {

    private Integer id;
    private Integer studentId;
    private Faculty faculty;

    public StudentDTO() {

    }

    public StudentDTO(Integer id, String username, String password, String firstName, String lastName, Integer nationalId, Integer studentId, Faculty faculty) {
        super(username, password, firstName, lastName, nationalId);
        this.id = id;
        this.studentId = studentId;
        this.faculty = faculty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object obj) {

        StudentDTO studentDTO = null;
        if (obj instanceof StudentDTO) studentDTO = (StudentDTO) obj;
        assert studentDTO != null;
        return Objects.equals(id, studentDTO.getId());
    }
}
