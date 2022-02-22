package com.example.sm.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class FacultyDTO {

	@Getter
	@Setter
	@NotEmpty(message = "name must not be empty")
	@Size(min = 2, max = 15)
	private String name;
}
