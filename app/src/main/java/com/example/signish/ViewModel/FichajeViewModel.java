package com.example.signish.ViewModel;

import android.content.Context;

import com.example.signish.Repository;

import java.io.IOException;

import androidx.lifecycle.ViewModel;


public class FichajeViewModel extends ViewModel {

    Repository miRepositorio = Repository.getRepository();


    public void setContext(Context context) {
        miRepositorio.setContext(context);
    }

    /*public void createEntry() {
        miRepositorio.createEntry();
    }*/

    public void guardar() {
        miRepositorio.guardar();
    }

    public void createEntradaFichaje(){
        miRepositorio.createFichajeEntrada();
    }

    public void createSalidaFichaje(){
        miRepositorio.createSalidaFichaje();
    }

    public void readEntry() throws IOException {
        miRepositorio.readEntry();
        //return miRepositorio.readEntry();

    }

}
