package com.example.signish;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private File file = null;


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

        FichajeDbHelper admin = new FichajeDbHelper(context);

        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor cursor = db.query("fichajes", null, null, null, null, null, null);


        Log.i("Fichaje", "Ha creado Entrada");





        //The following code is to create entry and save into a binary file.

//        ObjectOutputStream oos;
//        Fichaje fichaje = new Fichaje(Calendar.getInstance().getTime().toString());
//        file = new File(context.getFilesDir()+"/"+FILE_NAME);
//        try {
//            if (!file.exists()) {
//
//                FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
//                oos = new ObjectOutputStream(fos);
//                Log.i("nuevo fichero", "primera entrada");
//            } else {
//                Log.i("existe fichero", "siguiente entrada");
//                FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
//                oos = new ObjectOutputStream(fos) {
//                    protected void writeStreamHeader() throws IOException {
//                        reset();
//                    }
//                };
//            }
//
//            oos.writeObject(fichaje);
//            Log.i("Nueva entrada",  "fichaje guardado");
//            oos.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }

    }


    public void readEntry() throws IOException {

        FileInputStream fis2 = context.openFileInput(FILE_NAME);
        ObjectInputStream ois = new ObjectInputStream(fis2);

        Fichaje fichaje;
        //arreglar método
        try {
            do {

                fichaje = (Fichaje) ois.readObject();
                Log.i("PROBANDO Leer", "do while");
                System.out.println(fichaje.getCurrentTime());


            } while(fichaje != null);
            ois.close();
            //return fichaje.getCurrentTime();
        } catch (EOFException eo) {
            System.out.println("Fin");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();

    }


    public String currentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }


}

