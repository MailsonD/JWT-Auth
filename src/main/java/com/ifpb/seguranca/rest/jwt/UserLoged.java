package com.ifpb.seguranca.rest.jwt;

import java.io.Serializable;
import java.util.Objects;

public class UserLoged implements Serializable {

    private String email;
    private String token;

    public UserLoged(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public UserLoged() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoged userLoged = (UserLoged) o;
        return Objects.equals(email, userLoged.email) &&
                Objects.equals(token, userLoged.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, token);
    }
}
