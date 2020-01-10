package com.example.signish;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.signish.Data.UsuariosEsquema;
import com.example.signish.Data.User;

import androidx.annotation.Nullable;

public class UsuarioDbHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BBDD = "Usuarios.db";
    private static final int VERSION = 1;
    //private Context context;
    //User user;



    public UsuarioDbHelper(Context context){

        super(context, NOMBRE_BBDD, null, VERSION);
        Log.i("prueba", "constructor BBDD");
    }

    //constructor
    public UsuarioDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.i("prueba", "constructor BBDD2");
    }

    //Se ejecuta una única vez, establecemos la creación de las tablas y registros.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //mirar comandos SQL
        Log.i("prueba", "ON CREATE BBDD11");
        db.execSQL("CREATE TABLE " + UsuariosEsquema.UsuariosEntrada.TABLE_NAME + " ("
                + UsuariosEsquema.UsuariosEntrada._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UsuariosEsquema.UsuariosEntrada.ID + " TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.DNI + " TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.NOMBRE + " TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.APELLIDO + " TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.APELLIDO2 + " TEXT,"
                + UsuariosEsquema.UsuariosEntrada.HIJOS + " TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.DISCAPACIDAD + " TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.ROL + " TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.JORNADA + " TEXT NOT NULL,"
                + "UNIQUE (" + UsuariosEsquema.UsuariosEntrada.ID + "))");

        Log.i("prueba", "ON CREATE BBDD");
        //Contenedor de valores
        ContentValues values = new ContentValues();

        //Pares clave-valor
        values.put(UsuariosEsquema.UsuariosEntrada.ID,1);
        values.put(UsuariosEsquema.UsuariosEntrada.DNI,"47554785G");
        values.put(UsuariosEsquema.UsuariosEntrada.NOMBRE, "Maite");
        values.put(UsuariosEsquema.UsuariosEntrada.APELLIDO, "Navarro");
        values.put(UsuariosEsquema.UsuariosEntrada.APELLIDO2, "Abad");
        values.put(UsuariosEsquema.UsuariosEntrada.HIJOS, 0);
        values.put(UsuariosEsquema.UsuariosEntrada.DISCAPACIDAD, 0);
        values.put(UsuariosEsquema.UsuariosEntrada.ROL, "U"); //usuario o admin
        values.put(UsuariosEsquema.UsuariosEntrada.JORNADA, "N"); //normal o reducida


        //Insertar
        db.insert(UsuariosEsquema.UsuariosEntrada.TABLE_NAME,null, values);

        //insertar datos ficticios prueba inicial
        mockData(db);



    }

    //Para modificar la estructura de la tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //instrucciones para modificar el esquema de la base de datos, como por ejemplo eliminar
        //el esquema y recrearlo, agregar una nueva tabla, añadir una nueva columna, etc.
    }

    private void mockData(SQLiteDatabase sqLiteDatabase){
        mocKUser(sqLiteDatabase,new User("47778541","Marc","Soler", "Nose","1","33","U","N"));
        mocKUser(sqLiteDatabase,new User("X4788741D","Isha", "Noor", null, "0","0","A","N"));
        Log.i("prueba", "post mockData");
    }

    public long mocKUser(SQLiteDatabase db, User user) {
        return db.insert(
                UsuariosEsquema.UsuariosEntrada.TABLE_NAME,
                null,
                user.toContentValues());
    }

    //Recibirá una instancia User, convertirá a Contentvalues y luego se inserta:
    public long saveUser (User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                UsuariosEsquema.UsuariosEntrada.TABLE_NAME,null,user.toContentValues());


    }




}
