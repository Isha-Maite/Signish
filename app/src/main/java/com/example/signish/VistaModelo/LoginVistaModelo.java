package com.example.signish.VistaModelo;

import com.example.signish.Repository;

import androidx.lifecycle.ViewModel;

//Recibe instrucciones de vista para avisar controlador

public class LoginVistaModelo extends ViewModel {

    //Variables y atributos

    Repository miRepositorio = Repository.get();


    //funciones a partir de instrucciones de la vista

    public boolean userOk(String usuario, String password){

        return miRepositorio.userOk(usuario, password);
    }

}
