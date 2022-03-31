package com.example.sm.service.crudservice;

public interface CRUDServiceInterface<T, Q> {

    Q toDTO(T t);

    T fromDTO(Q q);
}
