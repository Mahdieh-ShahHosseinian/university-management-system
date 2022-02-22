package com.example.sm.dao;

import com.example.sm.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Manager findByUsername(String username);
}
