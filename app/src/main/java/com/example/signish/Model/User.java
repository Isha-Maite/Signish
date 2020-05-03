package com.example.signish.Model;

import org.json.JSONException;
import org.json.JSONObject;
public class User {

    private String nombre, apellido, email, urlfoto;

    public User(String nombre, String apellido, String email, String urlfoto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.urlfoto = urlfoto;
    }

    public User(){}

    public User(JSONObject jsonObject){
        try {
            nombre = jsonObject.getString("nombre");
            apellido = jsonObject.getString("apellido");
            email = jsonObject.getString("email");
            urlfoto = jsonObject.getString("foto");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }
}
