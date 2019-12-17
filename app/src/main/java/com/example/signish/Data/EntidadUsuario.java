package com.example.signish.Data;

import java.util.UUID;

public class EntidadUsuario {

    private String id;
    private String dni;
    private String nombre;
    private String apellido;
    private String apellido2;
    private String hijos;
    private String discapacidad;
    private String rol;
    private String jornada;

    public EntidadUsuario(String nombre, String dni, String apellido,String apellido2,String hijos, String discapacidad,
    String rol,String jornada){

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
