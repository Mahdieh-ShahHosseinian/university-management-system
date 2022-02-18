package com.example.sm.dao;

import com.example.sm.model.Faculty;
import com.example.sm.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByUsername(String username);
    Set<Student> findByFaculty(Faculty faculty);
}
