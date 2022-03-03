package com.example.sm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class StudentCourseId implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

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