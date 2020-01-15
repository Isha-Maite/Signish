package com.example.signish.Data;

import android.content.ContentValues;

import java.util.UUID;

public class EntryFichaje {

    private String id;
    private String currentTime;

    public EntryFichaje(String currentTime, String id) {
        this.currentTime = currentTime;
        this.id = UUID.randomUUID().toString();
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(FichajeEsquema.FichajeEntrada.ID, id);
        values.put(FichajeEsquema.FichajeEntrada.currentTime, currentTime);
        return values;
    }


    public String getCurrentTime() {
        return currentTime;
    }

    public String getId() {
        return id;
    }
}
