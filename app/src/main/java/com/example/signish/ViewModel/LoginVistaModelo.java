package com.example.signish.ViewModel;

import android.content.Context;

import com.example.signish.Repository;

import androidx.lifecycle.ViewModel;

//Recibe instrucciones de vista para avisar controlador

public class LoginVistaModelo extends ViewModel {

    //Variables y atributos

    Repository miRepositorio = Repository.get(getApplicationContext());

    public void setContext(Context context){
        miRepositorio.setContext(context);
    }
    //funciones a partir de instrucciones de la vista

    ///revisar mucho xD

    public Boolean userOk(String checkUser, String checkPassword) {

        return miRepositorio.userOk(checkUser,checkPassword);



    }

}
