package com.example.signish;

import android.content.Context;
import android.util.Log;

import com.example.signish.Model.Fichaje;
import com.example.signish.Model.Usuario;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
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


    //Método para verificar usuario correcto


    public Boolean userOk(String user, String password) throws IOException, ClassNotFoundException {

        Usuario usuario;

        FileInputStream filein = context.openFileInput("userFile.dat");
        ObjectInputStream ois = new ObjectInputStream(filein);

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


    public void createUser(Usuario usuario){

    }

    /*método para guardar fichajes, modificado para que si el fichero existe no añada la cabecera,
    Una cabecera que se genera sola en el fichero.
    Se sobreescribe el método writeStreamHeader
     */

    public void createEntry() {

        File file = new File(context.getFilesDir() + FILE_NAME);
        ObjectOutputStream oos;
        Fichaje fichaje = null;
        try {
            if (!file.exists()) {
                FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
                oos = new ObjectOutputStream(fos);
            } else {
                FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
                oos = new ObjectOutputStream(fos) {
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            }

            oos.writeObject(fichaje.getCurrentTime());
            Log.i("New entry saved at: ", fichaje.getCurrentTime());
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public String readEntry() throws IOException, ClassNotFoundException {

        Fichaje fichaje;
        FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);

        //arreglar método, solo entrará una vez con el return dentro
        try {
            while (true) {
                fichaje = (Fichaje) ois.readObject();
                Log.i("PROBANDO Leer", fichaje.getCurrentTime());
                ois.close();
                return fichaje.getCurrentTime();


            }
        } catch (EOFException eo) {
            System.out.println("Fin");
            ois.close();
        }
        return null;
    }


    public String currentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }


}

