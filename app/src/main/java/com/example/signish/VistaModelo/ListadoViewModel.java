package com.example.signish.VistaModelo;

import android.content.Context;

import com.example.signish.Repository;

import androidx.lifecycle.ViewModel;

public class ListadoViewModel extends ViewModel {
    Repository miRepositorio = Repository.get();

    public void setContext(Context context){
        miRepositorio.setContext(context);
    }

    //funciones a partir de instrucciones de la vista

}
