package com.example.signish;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.signish.Data.EntryFichaje;
import com.example.signish.Data.FichajeEsquema;

import java.util.Calendar;

import androidx.annotation.Nullable;

public class FichajeDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Fichaje.db";

    public FichajeDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("prueba", "Se crea el fichero para entradas de fichajes");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + FichajeEsquema.FichajeEntrada.TABLE_NAME + "("
                + FichajeEsquema.FichajeEntrada._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FichajeEsquema.FichajeEntrada.currentTime + " TEXT NOT NULL,"
                + "UNIQUE (" + FichajeEsquema.FichajeEntrada.currentTime + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
