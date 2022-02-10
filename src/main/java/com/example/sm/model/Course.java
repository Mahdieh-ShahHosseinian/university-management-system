package com.example.sm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "studentCourses"})
public class Course extends RepresentationModel<Course> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(unique = true, nullable = false)
    private int id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 1)
    private int unit;

    @ManyToOne
    private Professor professor;

    @OneToMany(mappedBy = "studentCourseId.course", fetch = FetchType.EAGER)
    private Set<StudentCourse> studentCourses;

    @ManyToOne
    private Faculty faculty;

    @Override
    public boolean equals(Object obj) {

        Course course = null;
        if (obj instanceof Course) course = (Course) obj;
        assert course != null;
        return id == course.getId();
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

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
