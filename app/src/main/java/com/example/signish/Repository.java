package com.example.signish;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.signish.Modelo.Usuario;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Repository {

    //Singleton

    private static Repository repositorio;
    private Context context;
    //private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private static String fileName = "userFile.txt";



    //Se genera constructor en privado para que no se haga más de un repositorio
    private Repository() {
    }

    //el método get será el encargado de llamar al constructor una única vez
    public static Repository get() {

        if (repositorio == null) {
            repositorio = new Repository();
        }
        return repositorio;
    }


    //la magia del extraño context

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }


    //Método para verificar usuario correcto


    public Boolean userOk(String user, String password) throws IOException, ClassNotFoundException {

        Log.i("PROBANDO", "He llegado al fichero");
        Usuario usuario;


        FileInputStream filein = context.openFileInput("userFile.dat");
        ObjectInputStream ois = new ObjectInputStream(filein);
        Log.i("LALALA", "A ver si llego al while");
        try {
            do {
                usuario = (Usuario)ois.readObject();
                Log.i("PROBANDO", usuario.getUsuario()+usuario.getPassword());
                if (user.equals(usuario.getUsuario()) && password.equals(usuario.getPassword())) {
                    ois.close();
                    Log.i("LALALA", "He hecho login");
                    return true;
                } else {
                    ois.close();
                    return false;
                }
            }while(usuario != null);
        } catch (EOFException eo){
            System.out.println ("Fin");
        }
        ois.close();
        return false;
    }
    
}







