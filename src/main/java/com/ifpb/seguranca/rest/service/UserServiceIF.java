package com.ifpb.seguranca.rest.service;

import com.ifpb.seguranca.rest.model.domain.User;

/**
 * @author Mailson Dennis
 * @email mailssondennis@gmail.com
 */
public interface UserServiceIF extends Service<User> {

    User auth(String email,String password);
}
