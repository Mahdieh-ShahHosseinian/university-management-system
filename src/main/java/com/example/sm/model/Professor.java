package com.example.sm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static com.example.sm.model.ApplicationUserRole.PROFESSOR;

@Entity
public class Professor extends ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer personnelId;

    @ManyToOne
    private Faculty faculty;

    // TODO change fetch type to lazy then solve addCourse() error on Service
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Course> courses;

    public Professor() {
    }

    public Professor(Integer id, String username, String password, String firstName, String lastName, Integer nationalId, Integer personnelId, Faculty faculty) {
        super(username, password, firstName, lastName, nationalId, PROFESSOR.getGrantedAuthorities());
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object obj) {

        Professor professor = null;
        if (obj instanceof Professor) professor = (Professor) obj;
        assert professor != null;
        return Objects.equals(personnelId, professor.getPersonnelId());
    }
}
