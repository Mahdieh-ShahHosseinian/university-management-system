package com.example.sm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "professors", "students", "courses"})
public class Faculty extends RepresentationModel<Faculty> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(unique = true, nullable = false)
    private int id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Professor> professors;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Student> students;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Course> courses;

    @Override
    public boolean equals(Object obj) {

        Faculty faculty = null;
        if (obj instanceof Faculty) faculty = (Faculty) obj;
        assert faculty != null;
        return id == faculty.getId();
    }

    // Setter & Getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}