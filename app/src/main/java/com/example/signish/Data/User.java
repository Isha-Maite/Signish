package com.example.signish.Data;

import android.content.ContentValues;

import java.util.UUID;

//Entidad USUARIO

public class User {

    private String id;
    private String dni;
    private String nombre;
    private String apellido;
    private String apellido2;
    private String hijos;
    private String discapacidad;
    private String rol;
    private String jornada;

    public User(String dni, String nombre, String apellido, String apellido2, String hijos, String discapacidad,
                String rol, String jornada){

        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
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
        values.put(UsuariosEsquema.UsuariosEntrada.APELLIDO2, apellido2);
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

    public String getApellido2() {
        return apellido2;
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
