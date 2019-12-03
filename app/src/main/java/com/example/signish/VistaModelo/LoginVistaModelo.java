package com.example.signish.VistaModelo;

import android.content.Context;

import com.example.signish.Modelo.Usuario;
import com.example.signish.Repository;

import java.io.IOException;

import androidx.lifecycle.ViewModel;

//Recibe instrucciones de vista para avisar controlador

public class LoginVistaModelo extends ViewModel {

    //Variables y atributos

    Repository miRepositorio = Repository.get();

    public void setContext(Context context){
        miRepositorio.setContext(context);
    }
    //funciones a partir de instrucciones de la vista

    ///revisar mucho xD

    public Boolean userOk(String checkUser, String checkPassword) throws IOException, ClassNotFoundException {

        return miRepositorio.userOk(checkUser,checkPassword);



    }

}
