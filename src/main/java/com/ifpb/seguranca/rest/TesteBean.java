package com.ifpb.seguranca.rest;

import com.ifpb.seguranca.rest.model.domain.User;
import com.ifpb.seguranca.rest.service.UserService;
import com.ifpb.seguranca.rest.service.UserServiceIF;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class TesteBean {

    @Inject
    private UserServiceIF userService;

    @PostConstruct
    private void init(){
        userService.save(new User("mailson@gmail.com","123","Mailson"));
    }

}
