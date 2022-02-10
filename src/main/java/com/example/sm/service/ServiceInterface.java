package com.example.sm.service;

import java.util.List;

public interface ServiceInterface<T> {

    // C R U D operation
    T save(T t);

    List<T> getAll();

    T get(int id);

    T update(T t, int id);

    void delete(int id);
}
