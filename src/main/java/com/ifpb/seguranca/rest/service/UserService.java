package com.ifpb.seguranca.rest.service;

import com.ifpb.seguranca.rest.model.dao.UserDao;
import com.ifpb.seguranca.rest.model.dao.UserDaoIF;
import com.ifpb.seguranca.rest.model.domain.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService implements UserServiceIF {

    @Inject
    private UserDaoIF userDao;

    @Override
    public void save(User object) {
        userDao.save(object);
    }

    @Override
    public void remove(Object key) {
        User user = find(key);
        if(user != null){
            userDao.remove(user);
        }
    }

    @Override
    public User find(Object key) {
        return userDao.find(key);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
