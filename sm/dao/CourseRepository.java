package com.example.sm.dao;

import com.example.sm.model.Course;
import com.example.sm.model.Faculty;
import com.example.sm.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Set<Course> findByFaculty(Faculty faculty);

    Set<Course> findByProfessor(Professor professor);
}
