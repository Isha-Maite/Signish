package com.example.signish.Model;

public class Fichaje{
    private String entrada;
    private String salida;
    private String user;

    public Fichaje(){

    }
    public Fichaje(String entrada, String salida, String user) {
        this.entrada = entrada;
        this.salida = salida;
        this.user = user;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Fichaje{" +
                "entrada='" + entrada + '\'' +
                ", salida='" + salida + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
