package com.example.sm.service.coreservice;

import com.example.sm.dao.FacultyRepository;
import com.example.sm.model.Faculty;
import com.example.sm.service.ServiceInterface;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyCoreService implements ServiceInterface<Faculty, Integer> {

    private final FacultyRepository repository;

    public FacultyCoreService(FacultyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Faculty save(Faculty faculty) {
        return repository.save(faculty);
    }

    @Override
    public List<Faculty> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Faculty> getAllPaginated(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public Faculty get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Faculty faculty, Integer id) {

        Faculty f = get(id);
        f.setName(faculty.getName());
        return save(f);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}