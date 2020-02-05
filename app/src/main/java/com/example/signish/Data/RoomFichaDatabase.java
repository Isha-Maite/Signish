package com.example.signish.Data;

import com.example.signish.RoomFicha;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities =  {RoomFicha.class}, version = 1)
public abstract class RoomFichaDatabase extends RoomDatabase {
    public abstract  FichaDao getFichaDao();
}
