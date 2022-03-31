package com.example.sm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static com.example.sm.model.ApplicationUserRole.ADMIN;

@Entity
public class Admin extends ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Admin() {

    }

    public Admin(Integer id, String username, String password, String firstName, String lastName, Integer nationalId) {
        super(username, password, firstName, lastName, nationalId, ADMIN.getGrantedAuthorities());
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
