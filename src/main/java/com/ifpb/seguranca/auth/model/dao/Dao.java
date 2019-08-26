package com.ifpb.seguranca.auth.model.dao;

import java.util.List;

/**
 * @author Mailson Deenis
 */
public interface Dao<T> {

    T save(T object);
    T find(Object key);
    void remove(T object);
    List<T> findAll();

}
