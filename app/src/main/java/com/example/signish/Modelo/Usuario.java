package com.example.signish.Modelo;

public class Usuario {

    String user;
    String password;

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUsuario() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
