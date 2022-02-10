package com.example.sm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sm.model.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

}
