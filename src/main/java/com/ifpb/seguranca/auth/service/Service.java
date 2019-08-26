package com.ifpb.seguranca.auth.service;

import java.util.List;

/**
 * @author Mailson Deenis
 */
public interface Service<T> {

    T save(T object);
    T find(Object key);
    void remove(T object);
    List<T> findAll();

}
