package com.ifpb.seguranca.rest.model.dao;

import java.util.List;

public interface Dao<T> {

    void save(T object);
    void remove(T object);
    T find(Object key);
    List<T> findAll();

}
