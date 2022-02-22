package com.example.sm.service;

import java.util.List;

public interface ServiceInterface<T> {

    // C R U D operation
    T save(T t);

    /**
     * Note that this is not an efficient way in real world applications
     * Say you have 50-000 elements and bomb!.. overloading and it causes poor performance
     * Instead you drop a page to send/retrieve less numbers of the entities
     *
     * @return all elements
     */
    List<T> getAll();

    T get(int id);

    T update(T t, int id);

    void delete(int id);
}
