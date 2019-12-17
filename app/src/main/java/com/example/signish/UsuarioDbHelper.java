package com.example.signish;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.signish.Data.UsuariosEsquema;

import androidx.annotation.Nullable;

public class UsuarioDbHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BBDD = "Usuarios.db";
    private static final int VERSION = 1;
    private Context context;

    interface Tablas{
        String CABECERA_USUARIO = "Cabecera_usuario";
    }


    public UsuarioDbHelper(Context context){
        super(context, NOMBRE_BBDD, null, VERSION);
    }

    //constructor
    public UsuarioDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Se ejecuta una única vez, establecemos la creación de las tablas y registros.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //mirar comandos SQL
        db.execSQL("CREATE TABLE " + UsuariosEsquema.UsuariosEntrada.TABLE_NAME + " ("
                + UsuariosEsquema.UsuariosEntrada.ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UsuariosEsquema.UsuariosEntrada.ID + "TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.NOMBRE + "TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.APELLIDO + "TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.APELLIDO2 + "TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.HIJOS + "TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.DISCAPACIDAD + "TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.ROL + "TEXT NOT NULL,"
                + UsuariosEsquema.UsuariosEntrada.JORNADA + "TEXT NOT NULL)");


    }

    //Para modificar la estructura de la tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nstrucciones para modificar el esquema de la base de datos, como por ejemplo eliminar
        //el esquema y recrearlo, agregar una nueva tabla, añadir una nueva columna, etc.
    }
}
