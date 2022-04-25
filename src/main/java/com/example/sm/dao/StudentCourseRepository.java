package com.example.sm.dao;

import com.example.sm.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {

    StudentCourse findByStudentCourseId(StudentCourseId studentCourseId);

    void deleteByStudentCourseId(StudentCourseId studentCourseId);

    Set<StudentCourse> findByStudentCourseIdProfessor(Professor studentCourseId_professor);

    StudentCourse findByStudentCourseIdStudentAndStudentCourseIdProfessorAndStudentCourseIdCourse(Student studentCourseId_student, Professor studentCourseId_professor, Course studentCourseId_course);
}
