package com.example.signish.ViewModel;

import android.content.Context;

import com.example.signish.Repository;

import androidx.lifecycle.ViewModel;

public class ListadoViewModel extends ViewModel {


    Repository miRepositorio = Repository.getRepository();

    public void setContext(Context context){
        miRepositorio.setContext(context);
    }

    //funciones a partir de instrucciones de la vista*/

}
