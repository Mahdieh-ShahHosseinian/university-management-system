package com.example.sm.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.sm.model.Faculty;

public class StudentDTO {
	
	private long nationalId;

	@NotEmpty(message = "firstName must not be empty")
	@Size(min = 2, max = 15)
	private String firstName;

	@NotEmpty(message = "lastName must not be empty")
	@Size(min = 2, max = 15)
	private String lastName;

	@NotNull(message = "faculty cannot be null")
	private Faculty faculty;

	public long getNationalId() {
		return nationalId;
	}

	public void setNationalId(long nationalId) {
		this.nationalId = nationalId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
}
