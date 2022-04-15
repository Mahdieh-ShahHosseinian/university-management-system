package com.example.sm.service;

import java.util.List;

public interface ServiceInterface<T, ID> {

    // C R U D operation
    T save(T t);

    List<T> getAll();

    List<T> getAllPaginated(int page, int size);

    T get(ID id);

    T update(T t, ID id);

    void delete(ID id);
}
