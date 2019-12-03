package com.example.signish;
import android.content.Context;
import com.example.signish.Model.Fichaje;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
public class Repository {

    private File binaryFile;
    private static final String FILE_NAME = "entradaSalida.txt";
    private static Repository repositorio;
    private Context context;
    private boolean fileExists = false;

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
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            ObjectOutputStream os = new ObjectOutputStream(fos);

            ArrayList<Fichaje> listaFichajes = new ArrayList<>();

            Fichaje fichaje = new Fichaje(currentTime());

            listaFichajes.add(fichaje);

            for (int counter = 0; counter < listaFichajes.size(); counter++) {
                String saveEntry = "Entrada  " + fichaje + "\n";
                os.write(saveEntry.getBytes());
            }

            os.write(fichaje.getCurrentTime().getBytes());

            os.close();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    public String currentTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }


}