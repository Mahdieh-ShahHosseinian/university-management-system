package com.example.sm.dto;

import com.example.sm.model.Faculty;
import com.example.sm.model.Professor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CourseDTO {

	@Getter
	@Setter
	@NotEmpty(message = "name must not be empty")
	@Size(min = 2, max = 15)
	private String name;

	@Getter
	@Setter
	@Range(min = 1, max = 5, message = "invalid unit")
	private int unit;

	@Getter
	@Setter
	@NotNull(message = "professor cannot be null")
	private Professor professor;

	@Getter
	@Setter
	@NotNull(message = "faculty cannot be null")
	private Faculty faculty;
}
