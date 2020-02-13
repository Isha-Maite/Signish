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

    /*@Query("SELECT*FROM Marcatge")
    List<RoomFicha> getMarcatges();

    @Query("SELECT * FROM Marcatge WHERE mId LIKE :uuid")
    RoomFicha getMarcatge(String uuid);*/

    @Query("SELECT * FROM Marcatge")
    List<RoomFicha> getMarcatgeEntrada();

    @Query("SELECT * FROM Marcatge")
    List<RoomFicha> getMarcatgeSalida();

    @Insert
    void addMarcatge(RoomFicha marca);

    @Delete
    void deleteMarcatge(RoomFicha marca);

    @Update
    void updateMarcatge(RoomFicha marca);
}
