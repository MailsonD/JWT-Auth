package com.ifpb.seguranca.auth.model.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Mailson Deenis
 */
@Entity
public class Usuario implements Serializable{
    @Id
    private String email;
    private String password;
    private String name;

    public Usuario() {
    }

    public Usuario(String email, String password, String nome) {
        this.email = email;
        this.password = password;
        this.name = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email) &&
                Objects.equals(password, usuario.password) &&
                Objects.equals(name, usuario.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, name);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", senha='" + password + '\'' +
                ", nome='" + name + '\'' +
                '}';
    }
}
