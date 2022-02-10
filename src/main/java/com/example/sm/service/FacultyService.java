package com.example.sm.service;

import com.example.sm.dao.FacultyRepository;
import com.example.sm.model.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService implements ServiceInterface<Faculty> {

    @Autowired
    private FacultyRepository repository;


    @Override
    public Faculty save(Faculty faculty) {
        return repository.save(faculty);
    }

    @Override
    public List<Faculty> getAll() {
        return repository.findAll();
    }

    @Override
    public Faculty get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Faculty faculty, int id) {

        Faculty f = get(id);
        f.setName(faculty.getName());
        return save(f);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
