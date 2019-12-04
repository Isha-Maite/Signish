package com.example.signish;

import android.content.Context;
import android.util.Log;

import com.example.signish.Model.Fichaje;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Repository {

    private static final String FILE_NAME = "entradaSalida.dat";
    private static Repository repositorio;
    private Context context;


    //Se genera constructor en privado para que no se haga más de un repositorio
    private Repository() {
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }

    //el método get será el encargado de llamar al constructor una única vez
    public static Repository get() {
        if (repositorio == null) {
            repositorio = new Repository();
        }
        return repositorio;
    }


    public Boolean createEntry() {
        try {

//            File fichero = new File("entradaSalida.dat");
//            FileOutputStream fileout = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
//            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
//
//            Fichaje fichaje = new Fichaje(currentTime());
//
//            dataOS.writeObject(fichaje);
//            dataOS.close();
//
//            FileOutputStream fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream){
//                protected void writeStreamHeader() throws IOException {
//                    reset();
//                }
//            };
//            objectOutputStream.writeObject(fichaje);
//
//            objectOutputStream.close();


            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);

            ArrayList<Fichaje> listaFichajes = new ArrayList<>();
            Fichaje fichaje = new Fichaje(currentTime());
            listaFichajes.add(fichaje);

            objectOutputStream.writeObject(fichaje);


            objectOutputStream.close();
            fos.close();


            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean readEntry() throws IOException, ClassNotFoundException {

        Fichaje fichaje;
        FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);

        try {
            while (true) {
                fichaje = (Fichaje) ois.readObject();
                Log.i("PROBANDO Leer", fichaje.getCurrentTime());

                return true;

            }
        } catch (EOFException eo) {
            System.out.println("Fin");
        }
        ois.close();
        return false;
    }


    public String currentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }


}

/*

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


 */