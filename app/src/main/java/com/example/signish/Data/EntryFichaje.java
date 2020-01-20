package com.example.signish.Data;

import android.content.ContentValues;

import java.util.UUID;

public class EntryFichaje {

    private String currentTime;

    public EntryFichaje(String currentTime, String id) {
        this.currentTime = currentTime;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(FichajeEsquema.FichajeEntrada.currentTime, currentTime);
        return values;
    }


    public String getCurrentTime() {
        return currentTime;
    }

}
