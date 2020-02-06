package com.example.signish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.signish.Data.FichajeEsquema;
import com.example.signish.Model.Fichaje;
import com.example.signish.Model.Mensaje;
import com.example.signish.Model.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
                if (user.equals("Maite")&&password.equals("1234")){
                    Log.i("Admin", "Usuario es admin");
                } else {
                    Log.i("Worker", "Usuario no es admin");
                }


                cursor.close();
                return true;
            }
        } cursor.close();
          return false;


    }


    /*método para guardar fichajes, modificado para que si el fichero existe no añada la cabecera,
    Una cabecera que se genera sola en el fichero.
    Se sobreescribe el método writeStreamHeader*/
    public void createEntry() {

        FichajeDbHelper admin = new FichajeDbHelper(context);

        SQLiteDatabase db = admin.getReadableDatabase();

        ContentValues values = new ContentValues();

        //values.put(FichajeEsquema.FichajeEntrada.ID, (byte[]) null);
        values.put(FichajeEsquema.FichajeEntrada.currentTime, Calendar.getInstance().getTime().toString());

        // Insertar...
        db.insert(FichajeEsquema.FichajeEntrada.TABLE_NAME, null, values);

        Log.i("Fichaje", "Ha creado Entrada");

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

    public void WriteMsgFirebase(String msg){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference msg_reference = db.child("feedbackMessages").child("mensajes").push();
        msg_reference.setValue(new Mensaje("usuario inventado",
                "pVBEOgINbVOGdkne7Sjhg3ycW6h2", msg));
        db.child("feedbackMessages").child("usuarios").child("pVBEOgINbVOGdkne7Sjhg3ycW6h2").child("mensaje")
                .setValue(msg_reference.getKey());
    }

    Thread connectPostgres = new Thread(){
        public void run(){
            Connection conn = null;
            try{
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.22/", "angela", "ruizrobles");

                Statement st = conn.createStatement();

                st.execute(CREATE_POSTGRES);

            }catch (SQLException e){
                Log.i("Exception", "----------- Ha Fallado SQL -----------------");
            }
            catch (ClassNotFoundException c){
                Log.i("Exception", "----------- No se encuentra classe -----------------");

            }
            finally {
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

    };

    private static final String CREATE_POSTGRES = "CREATE TABLE IF NOT EXISTS FICHAJE"
            + "("
            +  FichajeEsquema.FichajeEntrada.currentTime + " TEXT NOT NULL,"
            + "UNIQUE (" + FichajeEsquema.FichajeEntrada.currentTime + "))";

    private static String INSERT_POSTGRES = "";

    public void createFichajePostgres(){

        connectPostgres.start();
        if (connectPostgres.isAlive()){
            Log.i("Connection", "Ha podido Connectar");
        } else {

            Log.i("Connection failed", "No ha podido connectar");
        }


    }

    public String currentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }


}

