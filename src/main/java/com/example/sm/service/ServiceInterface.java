package com.example.sm.service;

import java.util.List;

public interface ServiceInterface<T> {

    // C R U D operation
    T save(T t);

    List<T> getAll();

    List<T> getAllPaginated(int page, int size);

    T get(Integer id);

    T update(T t, Integer id);

    void delete(Integer id);
}
