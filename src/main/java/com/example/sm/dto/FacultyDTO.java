package com.example.sm.dto;

import org.springframework.hateoas.RepresentationModel;

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
}
