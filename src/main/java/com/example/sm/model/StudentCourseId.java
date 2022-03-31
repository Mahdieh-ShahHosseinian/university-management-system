package com.example.sm.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;

@Embeddable
public class StudentCourseId implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;

    public StudentCourseId() {

    }

    public StudentCourseId(Student student) {
        this.student = student;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
