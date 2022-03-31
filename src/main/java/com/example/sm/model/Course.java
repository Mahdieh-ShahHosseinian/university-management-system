package com.example.sm.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 1)
    private Integer unit;

    @ManyToOne
    private Faculty faculty;

    @ManyToMany(mappedBy = "courses")
    private Set<Professor> professors;

    @OneToMany(mappedBy = "studentCourseId.course", fetch = FetchType.EAGER)
    private Set<StudentCourse> studentCourses;

    public Course() {

    }

    public Course(Integer id, String name, Integer unit, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.faculty = faculty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer courseId) {
        this.id = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    @Override
    public boolean equals(Object obj) {

        Course course = null;
        if (obj instanceof Course) course = (Course) obj;
        assert course != null;
        return Objects.equals(id, course.getId());
    }
}
