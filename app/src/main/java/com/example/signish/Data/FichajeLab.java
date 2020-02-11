package com.example.signish.Data;


import android.annotation.SuppressLint;
import android.content.Context;

import com.example.signish.RoomFicha;

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

        public List<RoomFicha> getMarcatges(){
            return mFichaDao.getMarcatges();
        }

        public RoomFicha getMarcatge(String id){
            return mFichaDao.getMarcatge(id);
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
