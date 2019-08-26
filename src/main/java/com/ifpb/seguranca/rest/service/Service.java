package com.ifpb.seguranca.rest.service;

import java.util.List;

public interface Service<T> {

    void save(T object);
    void remove(Object key);
    T find(Object key);
    List<T> findAll();
}
