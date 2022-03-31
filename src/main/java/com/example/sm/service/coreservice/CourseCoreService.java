package com.example.sm.service.coreservice;

import com.example.sm.dao.CourseRepository;
import com.example.sm.model.Course;
import com.example.sm.service.ServiceInterface;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseCoreService implements ServiceInterface<Course> {

    private final CourseRepository repository;

    public CourseCoreService(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Course save(Course course) {
        return repository.save(course);
    }

    @Override
    public List<Course> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Course> getAllPaginated(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public Course get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Course update(Course course, Integer id) {

        Course c = get(id);
        c.setName(course.getName());
        c.setUnit(course.getUnit());
        c.setFaculty(course.getFaculty());
        return save(c);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}