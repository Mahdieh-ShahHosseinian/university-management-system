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

    public StudentCourse(Student student, Professor professor, Course course) {
        studentCourseId = new StudentCourseId(student, professor, course);
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

    public Student getStudent() {
        return studentCourseId.getStudent();
    }

    public Professor getProfessor() {
        return studentCourseId.getProfessor();
    }

    public Course getCourse() {
        return studentCourseId.getCourse();
    }

    @Override
    public boolean equals(Object obj) {

        StudentCourse studentCourse = null;
        if (obj instanceof StudentCourse) studentCourse = (StudentCourse) obj;
        assert studentCourse != null;
        return studentCourseId == studentCourse.getStudentCourseId();
    }
}
