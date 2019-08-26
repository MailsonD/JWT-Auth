package com.ifpb.seguranca.auth.service;

import com.ifpb.seguranca.auth.model.dao.UsuarioDao;
import com.ifpb.seguranca.auth.model.domain.Usuario;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Mailson Deenis
 */
public class UsuarioService implements Service<Usuario>{

    @Inject
    private UsuarioDao usuarioDao;

    @Override
    public Usuario save(Usuario object) {
        return usuarioDao.save(object);
    }

    @Override
    public Usuario find(Object key) {
        return usuarioDao.find(key);
    }

    @Override
    public void remove(Usuario object) {
        usuarioDao.remove(object);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }

    public Usuario auth(String email, String password){
        Usuario user = usuarioDao.find(email);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
