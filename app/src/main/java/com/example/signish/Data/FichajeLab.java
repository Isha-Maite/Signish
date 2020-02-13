package com.example.signish.Data;


import android.annotation.SuppressLint;
import android.content.Context;

import com.example.signish.Model.RoomFicha;

import java.util.List;

import androidx.room.Room;

/**
 * Esta clase hace uso de la interfaz FichaDao e interactúa con la bbdd. */
public class FichajeLab {

    @SuppressLint("StaticFieldLeak")
    private static FichajeLab sFichajeLab;

    private FichaDao mFichaDao;

    private FichajeLab(Context context) {
        RoomFichaDatabase database = Room.databaseBuilder(context, RoomFichaDatabase.class,
                "marcatge").allowMainThreadQueries().build();
        mFichaDao = database.getFichaDao();
    }

        public static FichajeLab get (Context context){
            if(sFichajeLab == null){
                sFichajeLab = new FichajeLab(context);
            }
            return sFichajeLab;
        }

        public List<RoomFicha> getMarcatgeEntrada(){
            return mFichaDao.getMarcatgeEntrada();
        }

        public List<RoomFicha> getMarcatgeSalida(){
            return mFichaDao.getMarcatgeSalida();
        }


        public void addMarcatge(RoomFicha marca){
            mFichaDao.addMarcatge(marca);
        }

        public void updateMarcatge(RoomFicha marca){
        mFichaDao.updateMarcatge(marca);
        }

        public void deleteMarcatge(RoomFicha marca){
        mFichaDao.deleteMarcatge(marca);
        }

}
