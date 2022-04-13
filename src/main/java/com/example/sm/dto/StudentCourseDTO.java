package com.example.sm.dto;

import com.example.sm.model.StudentCourse;
import com.example.sm.model.StudentCourseId;

public class StudentCourseDTO {

    private StudentCourseId studentCourseId;
    private Double grade;

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

        StudentCourseDTO studentCourseDTO = null;
        if (obj instanceof StudentCourseDTO) studentCourseDTO = (StudentCourseDTO) obj;
        assert studentCourseDTO != null;
        return studentCourseId == studentCourseDTO.getStudentCourseId();
    }
}
