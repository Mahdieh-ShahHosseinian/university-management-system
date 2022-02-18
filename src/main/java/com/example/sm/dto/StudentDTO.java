package com.example.sm.dto;

import com.example.sm.model.Faculty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentDTO {

    @Getter
    @Setter
    private long nationalId;

    @Getter
    @Setter
    @NotEmpty(message = "firstName must not be empty")
    @Size(min = 2, max = 15)
    private String firstName;

    @Getter
    @Setter
    @NotEmpty(message = "lastName must not be empty")
    @Size(min = 2, max = 15)
    private String lastName;

    @Getter
    @Setter
    @NotEmpty(message = "firstName must not be empty")
    @Size(min = 4, max = 15)
    private String username;

    @Getter
    @Setter
    @NotEmpty(message = "firstName must not be empty")
    @Size(min = 6, max = 15)
    private String password;

    @Getter
    @Setter
    @NotNull(message = "faculty cannot be null")
    private Faculty faculty;
}
