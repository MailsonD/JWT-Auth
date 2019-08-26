package com.ifpb.seguranca.rest.model.dao;

import java.util.List;

/**
 * @author Mailson Dennis
 * @email mailssondennis@gmail.com
 */
public interface Dao<T> {

    void save(T object);
    void remove(T object);
    T find(Object key);
    List<T> findAll();

}
