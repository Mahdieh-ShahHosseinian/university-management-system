package com.example.sm.model;

import javax.persistence.*;
import java.util.Set;

import static com.example.sm.model.ApplicationUserRole.STUDENT;

@Entity
public class Student extends ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer studentId;

    @ManyToOne
    private Faculty faculty;

    @OneToMany(mappedBy = "studentCourseId.student", fetch = FetchType.EAGER)
    private Set<StudentCourse> studentCourses;

    public Student() {

    }

    public Student(Integer id, String username, String password, String firstName, String lastName, Integer nationalId, Integer studentId, Faculty faculty) {
        super(username, password, firstName, lastName, nationalId, STUDENT.getGrantedAuthorities());
        this.id = id;
        this.studentId = studentId;
        this.faculty = faculty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
}
