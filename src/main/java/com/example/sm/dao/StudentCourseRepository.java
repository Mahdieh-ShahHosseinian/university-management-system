package com.example.sm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sm.model.StudentCourse;
import com.example.sm.model.StudentCourseId;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {

	StudentCourse findByStudentCourseId(StudentCourseId studentCourseId);
	void deleteByStudentCourseId(StudentCourseId studentCourseId);
}