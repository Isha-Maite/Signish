package com.example.signish.Data;

import android.content.ContentValues;

import java.util.UUID;

//Entidad USUARIO

public class User {

    private String id;
    private String dni;
    private String nombre;
    private String apellido;
    private String contrasenya;
    private String hijos;
    private String discapacidad;
    private String rol;
    private String jornada;

    public User(String dni, String nombre, String apellido, String contrasenya, String hijos, String discapacidad,
                String rol, String jornada){

        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenya = contrasenya;
        this.hijos = hijos;
        this.discapacidad = discapacidad;
        this.rol = rol;
        this.jornada = jornada;

    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(UsuariosEsquema.UsuariosEntrada.ID, id);
        values.put(UsuariosEsquema.UsuariosEntrada.DNI, dni);
        values.put(UsuariosEsquema.UsuariosEntrada.NOMBRE, nombre);
        values.put(UsuariosEsquema.UsuariosEntrada.APELLIDO, apellido);
        values.put(UsuariosEsquema.UsuariosEntrada.CONTRASENYA, contrasenya);
        values.put(UsuariosEsquema.UsuariosEntrada.HIJOS, hijos);
        values.put(UsuariosEsquema.UsuariosEntrada.DISCAPACIDAD, discapacidad);
        values.put(UsuariosEsquema.UsuariosEntrada.ROL, rol); //usuario o admin
        values.put(UsuariosEsquema.UsuariosEntrada.JORNADA, jornada); //normal o reducida
        return values;

    }

    public String getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getcontrasenya() {
        return contrasenya;
    }

    public String getHijos() {
        return hijos;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public String getRol() {
        return rol;
    }

    public String getJornada() {
        return jornada;
    }
}
