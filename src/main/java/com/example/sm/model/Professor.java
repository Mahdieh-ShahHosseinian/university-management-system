package com.example.sm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "courses"})
public class Professor extends RepresentationModel<Professor> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(unique = true, nullable = false)
    private int personnelId;

    @Column(unique = true, nullable = false, length = 10)
    private long nationalId;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
    private Set<Course> courses;

    @ManyToOne
    private Faculty faculty;

    @Override
    public boolean equals(Object obj) {

        Professor professor = null;
        if (obj instanceof Professor) professor = (Professor) obj;
        assert professor != null;
        return personnelId == professor.getPersonnelId();
    }

    // Setter & Getter

    public int getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(int personnelId) {
        this.personnelId = personnelId;
    }

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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
