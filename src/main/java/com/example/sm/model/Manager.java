package com.example.sm.model;

import javax.persistence.*;
import java.util.Objects;

import static com.example.sm.model.ApplicationUserRole.MANAGER;

@Entity
public class Manager extends ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer personnelId;

    @ManyToOne
    private Faculty faculty;

    public Manager() {
        setGrantedAuthorities(MANAGER.getGrantedAuthorities());
    }

    public Manager(Integer id, String username, String password, String firstname, String lastname, Integer nationalId, Integer personnelId, Faculty faculty) {
        super(username, password, firstname, lastname, nationalId, MANAGER.getGrantedAuthorities());
        this.id = id;
        this.personnelId = personnelId;
        this.faculty = faculty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object obj) {

        Manager manager = null;
        if (obj instanceof Manager) manager = (Manager) obj;
        assert manager != null;
        return Objects.equals(personnelId, manager.getPersonnelId());
    }
}
