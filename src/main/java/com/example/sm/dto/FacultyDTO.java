package com.example.sm.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class FacultyDTO {

	@NotEmpty(message = "name must not be empty")
	@Size(min = 2, max = 15)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
