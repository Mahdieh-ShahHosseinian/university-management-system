package com.example.sm.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Professor> professors;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Student> students;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Course> courses;

    public Faculty() {

    }

    public Faculty(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer facultyId) {
        this.id = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object obj) {
        Faculty faculty = null;
        if (obj instanceof Faculty) faculty = (Faculty) obj;
        assert faculty != null;
        return Objects.equals(id, faculty.getId());
    }
}
