package com.example.sm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class StudentCourseId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @ManyToOne
    private Student student;

    @Getter
    @Setter
    @ManyToOne
    private Course course;

    public StudentCourseId() {

    }

    public StudentCourseId(Student student, Course course) {
        this.student = student;
        this.course = course;
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
