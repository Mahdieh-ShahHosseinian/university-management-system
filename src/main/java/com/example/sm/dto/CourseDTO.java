package com.example.sm.dto;

import com.example.sm.model.Faculty;
import com.example.sm.model.Professor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CourseDTO {

	@NotEmpty(message = "name must not be empty")
	@Size(min = 2, max = 15)
	private String name;

	@Range(min = 1, max = 5, message = "invalid unit")
	private int unit;

	@NotNull(message = "professor cannot be null")
	private Professor professor;

	@NotNull(message = "faculty cannot be null")
	private Faculty faculty;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
}
