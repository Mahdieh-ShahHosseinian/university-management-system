package com.example.sm.dto;

import com.example.sm.model.Faculty;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class FacultyDTO extends RepresentationModel<FacultyDTO> {

    private Integer id;
    private String name;

    public FacultyDTO() {

    }

    public FacultyDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        FacultyDTO facultyDTO = null;
        if (obj instanceof FacultyDTO) facultyDTO = (FacultyDTO) obj;
        assert facultyDTO != null;
        return Objects.equals(id, facultyDTO.getId());
    }
}
