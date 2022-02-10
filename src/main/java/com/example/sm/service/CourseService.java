package com.example.sm.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sm.dao.CourseRepository;
import com.example.sm.model.Course;
import com.example.sm.model.Faculty;
import com.example.sm.model.Professor;

@Service
public class CourseService implements ServiceInterface<Course> {

    @Autowired
    private CourseRepository repository;


    @Override
    public Course save(Course course) {
        return repository.save(course);
    }

    @Override
    public List<Course> getAll() {
        return repository.findAll();
    }

    @Override
    public Course get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Course update(Course course, int id) {

        Course c = get(id);
        c.setName(course.getName());
        c.setUnit(course.getUnit());
        c.setProfessor(course.getProfessor());
        c.setFaculty(course.getFaculty());
        return save(c);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }


    public Set<Course> findByFaculty(Faculty faculty) {
        return repository.findByFaculty(faculty);
    }

    public Set<Course> findByProfessor(Professor professor) {
        return repository.findByProfessor(professor);
    }
}
