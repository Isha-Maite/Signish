package com.example.signish;

import com.example.signish.Modelo.Usuario;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Repository {

    //Singleton
    private static Repository repositorio;

    //Se genera constructor en privado para que no se haga más de un repositorio
    private Repository (){
    }

    //el método get será el encargado de llamar al constructor una única vez
    public static Repository get(){

        if(repositorio == null){
            repositorio = new Repository();
        }
        return repositorio;
    }

    //Método para verificar usuario correcto

    public Boolean userOk (String checkUser, String checkPassword) throws IOException {

        Usuario usuario;

        File fichero = new File("FicheroPersona.dat");
        FileInputStream filein = new FileInputStream (fichero); //necesario para leer fichero
        ObjectInputStream dataIS = new ObjectInputStream (filein) ; //para leer objetos
        try {
            while (true) {
                usuario = (Usuario) dataIS.readObject (); //para usar readObject es necesario ObjectInputStream

                if (checkUser.equals(usuario.getUsuario()) && checkPassword.equals(usuario.getPassword())){
                    return true;
                }
            }
        } catch (EOFException | ClassNotFoundException eo) {
            System.out.println ("Error de lectura.");
        }
        dataIS.close();

        return false;
    }

}
