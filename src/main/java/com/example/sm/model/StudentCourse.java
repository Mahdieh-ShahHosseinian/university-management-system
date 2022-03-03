package com.example.sm.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AssociationOverrides({
        @AssociationOverride(name = "studentCourseId.student", joinColumns = @JoinColumn(name = "student_id")),
        @AssociationOverride(name = "studentCourseId.course", joinColumns = @JoinColumn(name = "course_id"))})
public class StudentCourse extends RepresentationModel<StudentCourse> {

    @EmbeddedId
    private StudentCourseId studentCourseId = new StudentCourseId();

    private double grade;

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
}