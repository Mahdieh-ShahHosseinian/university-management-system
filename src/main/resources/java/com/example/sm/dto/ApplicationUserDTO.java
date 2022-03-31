package com.example.sm.dto;

import org.springframework.hateoas.RepresentationModel;

public class ApplicationUserDTO<T extends RepresentationModel<? extends T>> extends RepresentationModel<T> {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Integer nationalId;
    private byte[] profilePicture;

    public ApplicationUserDTO() {

    }

    public ApplicationUserDTO(String username, String password, String firstName, String lastName, Integer nationalId) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
