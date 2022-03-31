package com.example.sm.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class StudentCourse {

    @EmbeddedId
    private StudentCourseId studentCourseId;

    private Double grade;

    public StudentCourse() {

    }

    public StudentCourse(Professor professor, Course course, Student student) {
        assert false;
        studentCourseId.setProfessor(professor);
        studentCourseId.setCourse(course);
        studentCourseId.setStudent(student);
    }

    public StudentCourseId getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(StudentCourseId studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object obj) {

        StudentCourse studentCourse = null;
        if (obj instanceof StudentCourse) studentCourse = (StudentCourse) obj;
        assert studentCourse != null;
        return studentCourseId == studentCourse.getStudentCourseId();
    }
}
