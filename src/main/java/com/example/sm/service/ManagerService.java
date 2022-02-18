package com.example.sm.service;

import com.example.sm.dao.ManagerRepository;
import com.example.sm.model.Manager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManagerService implements ServiceInterface<Manager> {

    private ManagerRepository repository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Manager save(Manager manager) {

        String encodedPass = bCryptPasswordEncoder.encode(manager.getPassword());
        manager.setPassword(encodedPass);
        return repository.save(manager);
    }

    @Override
    public List<Manager> getAll() {
        return repository.findAll();
    }

    @Override
    public Manager get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Manager update(Manager manager, int id) {

        Manager m = get(id);
        m.setManagerId(manager.getManagerId());
        m.setFirstName(manager.getFirstName());
        m.setLastName(manager.getLastName());
        return save(m);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
