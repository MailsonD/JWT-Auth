package com.ifpb.seguranca.rest.model.dao;

import com.ifpb.seguranca.rest.model.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Mailson Dennis
 * @email mailssondennis@gmail.com
 */
@Stateless
public class UserDao implements UserDaoIF {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(User object) {
        em.persist(object);
    }

    @Override
    public void remove(User object) {
        em.remove(em.merge(object));
    }

    @Override
    public User find(Object key) {
        return em.find(User.class, key);
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT u FROM User u";
        return em.createQuery(query,User.class).getResultList();
    }
}
