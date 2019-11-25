package com.example.signish.VistaModelo;

import com.example.signish.Modelo.Usuario;
import com.example.signish.Repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import androidx.lifecycle.ViewModel;

//Recibe instrucciones de vista para avisar controlador

public class LoginVistaModelo extends ViewModel {

    //Variables y atributos

    Repository miRepositorio = Repository.get();


    //funciones a partir de instrucciones de la vista



    ///revisar mucho xD

    public Boolean userOk(String checkUser, String checkPassword) throws IOException {


        return miRepositorio.userOk(checkUser,checkPassword);
    }

}
