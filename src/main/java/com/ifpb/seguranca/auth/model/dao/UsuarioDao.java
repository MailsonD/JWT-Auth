package com.ifpb.seguranca.auth.model.dao;

import com.ifpb.seguranca.auth.model.domain.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Mailson Deenis
 */
@Stateless
public class UsuarioDao implements Dao<Usuario> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Usuario save(Usuario object) {
        em.persist(object);
        return object;
    }

    @Override
    public Usuario find(Object key) {
        return em.find(Usuario.class, key);
    }

    @Override
    public void remove(Usuario object) {
        em.remove(em.merge(object));
    }

    @Override
    public List<Usuario> findAll() {
        String query = "SELECT u FROM Usuario u";
        return em.createQuery(query, Usuario.class).getResultList();
    }
}
