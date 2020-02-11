package com.example.signish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.signish.Data.FichajeEsquema;
import com.example.signish.Model.Fichaje;
import com.example.signish.Model.Usuario;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
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

    String userSq;
    String passSq;


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
    public Boolean userOk(String user, String password) {

        UsuarioDbHelper admin = new UsuarioDbHelper(context);
        SQLiteDatabase db = admin.getReadableDatabase();
        //se crea objeto cursor para guardar los datos de la consulta
        Cursor cursor = db.rawQuery("SELECT nombre, contrasenya FROM usuarios", null);

        //se recorren los datos con un while
        while(cursor.moveToNext()){
            userSq = cursor.getString(cursor.getColumnIndex("nombre"));
            passSq = cursor.getString(cursor.getColumnIndex("contrasenya"));
            if(user.equals(userSq)&&password.equals(passSq)){
                Log.i("lala","Login con QLIte");
                cursor.close();
                return true;
            }
        } cursor.close();
          return false;


    }


    /*método para guardar fichajes, modificado para que si el fichero existe no añada la cabecera,
    Una cabecera que se genera sola en el fichero.
    Se sobreescribe el método writeStreamHeader*/
    /*public void createEntry() {

        FichajeDbHelper admin = new FichajeDbHelper(context);

        SQLiteDatabase db = admin.getReadableDatabase();

        ContentValues values = new ContentValues();

        //values.put(FichajeEsquema.FichajeEntrada.ID, (byte[]) null);
        values.put(FichajeEsquema.FichajeEntrada.currentTime, Calendar.getInstance().getTime().toString());

        // Insertar...
        db.insert(FichajeEsquema.FichajeEntrada.TABLE_NAME, null, values);

        Log.i("Fichaje", "Ha creado Entrada");

    }*/

    public void guardar(){

        String marcatge = Calendar.getInstance().getTime().toString();


    }


    public void readEntry() throws IOException {

        FichajeDbHelper admin = new FichajeDbHelper(context);

        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT currenttime from fichajes", null);
        while(cursor.moveToNext()){
            String currentimeSql = cursor.getString(cursor.getColumnIndex("currentTime"));

            System.out.print("\n" + currentimeSql + "\n");
        } cursor.close();

    }


    public String currentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }


}

