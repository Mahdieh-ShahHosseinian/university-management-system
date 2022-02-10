package com.example.sm.controller;

import java.util.List;

public interface ControllerInterface<T, Q> {

    T save(Q q);

    List<T> getAll();

    T get(int id);

    T update(Q q, int id);

    void delete(int id);
}
