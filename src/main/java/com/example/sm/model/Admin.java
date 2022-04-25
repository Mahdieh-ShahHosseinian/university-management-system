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
        setGrantedAuthorities(ADMIN.getGrantedAuthorities());
    }

    public Admin(Integer id, String username, String password, String firstname, String lastname, Integer nationalId) {
        super(username, password, firstname, lastname, nationalId, ADMIN.getGrantedAuthorities());
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
