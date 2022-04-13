package com.example.sm.dto;

import com.example.sm.model.Course;
import com.example.sm.model.Faculty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;


public class CourseDTO extends RepresentationModel<CourseDTO> {

    private Integer id;
    private String name;
    private Integer unit;
    private Faculty faculty;

    public CourseDTO() {
    }

    public CourseDTO(Integer id, String name, Integer unit, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.faculty = faculty;
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

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object obj) {

        CourseDTO courseDTO = null;
        if (obj instanceof CourseDTO) courseDTO = (CourseDTO) obj;
        assert courseDTO != null;
        return Objects.equals(id, courseDTO.getId());
    }
}
