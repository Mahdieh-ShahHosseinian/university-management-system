package com.example.sm.model;

import org.springframework.hateoas.RepresentationModel;

import java.beans.Transient;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "studentCourseId.student", joinColumns = @JoinColumn(name = "student_id")),
        @AssociationOverride(name = "studentCourseId.course", joinColumns = @JoinColumn(name = "course_id")) })
public class StudentCourse extends RepresentationModel<StudentCourse> {

    @EmbeddedId
    private StudentCourseId studentCourseId = new StudentCourseId();

    private double grade;

    public StudentCourse() {

    }

    public StudentCourse(Student student, Course course) {

        studentCourseId.setStudent(student);
        studentCourseId.setCourse(course);
    }

    @Override
    public boolean equals(Object obj) {

        StudentCourse studentCourse = null;
        if (obj instanceof StudentCourse) studentCourse = (StudentCourse) obj;
        assert studentCourse != null;
        return studentCourseId == studentCourse.getStudentCourseId();
    }

    // Setter & Getter

    @Transient
    public Student getStudent() {
        return studentCourseId.getStudent();
    }

    public void setStudent(Student student) {
        studentCourseId.setStudent(student);
    }

    @Transient
    public Course getCourse() {
        return studentCourseId.getCourse();
    }

    public void setCourse(Course course) {
        studentCourseId.setCourse(course);
    }

    public StudentCourseId getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(StudentCourseId studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}