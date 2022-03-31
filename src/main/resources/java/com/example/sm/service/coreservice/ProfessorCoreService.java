package com.example.sm.service.coreservice;

import com.example.sm.dao.ProfessorRepository;
import com.example.sm.model.Professor;
import com.example.sm.service.ServiceInterface;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorCoreService implements ServiceInterface<Professor> {

    private final ProfessorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ProfessorCoreService(ProfessorRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Professor save(Professor professor) {

        String encodedPass = passwordEncoder.encode(professor.getPassword());
        professor.setPassword(encodedPass);
        return repository.save(professor);
    }

    @Override
    public List<Professor> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Professor> getAllPaginated(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public Professor get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Professor update(Professor professor, Integer id) {

        Professor p = get(id);
        p.setUsername(professor.getUsername());
        p.setPassword(professor.getPassword());
        p.setNationalId(professor.getNationalId());
        p.setFirstname(professor.getFirstname());
        p.setLastname(professor.getLastname());
        p.setFaculty(professor.getFaculty());
        return save(p);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
