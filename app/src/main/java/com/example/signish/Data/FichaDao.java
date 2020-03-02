package com.example.signish.Data;

import com.example.signish.Model.RoomFicha;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface FichaDao {

    @Query("SELECT * FROM Marcatge")
    List<RoomFicha> getMarcatges();

    @Insert
    void addMarcatge(RoomFicha marca);

    @Delete
    void deleteMarcatge(RoomFicha marca);

    @Update
    void updateMarcatge(RoomFicha marca);
}
