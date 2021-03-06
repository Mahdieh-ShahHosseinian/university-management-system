package com.example.sm.dto;

import org.springframework.hateoas.RepresentationModel;

public class ApplicationUserDTO<T extends RepresentationModel<? extends T>> extends RepresentationModel<T> {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Integer nationalId;
    private byte[] profilePicture;

    public ApplicationUserDTO() {

    }

    public ApplicationUserDTO(String username, String password, String firstname, String lastname, Integer nationalId) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
