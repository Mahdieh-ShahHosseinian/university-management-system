package com.example.sm.dao;

import com.example.sm.model.Faculty;
import com.example.sm.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    Set<Professor> findByFaculty(Faculty faculty);
}
