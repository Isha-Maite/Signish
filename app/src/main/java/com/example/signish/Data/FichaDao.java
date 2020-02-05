package com.example.signish.Data;

import com.example.signish.RoomFicha;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
interface FichaDao {

    @Query("SELECT*FROM marcatge")
    List<RoomFicha> getMarcatges();

    @Query("SELECT * FROM marcatge WHERE mId LIKE :uuid")
    RoomFicha getMarcatge(String uuid);

    @Insert
    void addMarcatge(RoomFicha marca);

    @Delete
    void deleteMarcatge(RoomFicha marca);

    @Update
    void updateMarcatge(RoomFicha marca);
}
