package com.example.sm.controller;

import com.example.sm.dto.FacultyDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ControllerInterface<T> {

    T save(T t);

    List<T> getAll();

    List<T> getAllPaginated(int page, int size);

    T get(int id);

    T update(T t, int id);

    void delete(int id);
}
