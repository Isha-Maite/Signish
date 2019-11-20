package com.example.signish;

public class Repository {

    //Singleton
    private static Repository repositorio;

    //Se genera constructor en privado para que no se haga más de un repositorio
    private Repository (){
    }

    //el método get será el encargado de llamar al constructor una única vez
    public static Repository get(){

        if(repositorio == null){
            repositorio = new Repository();
        }
        return repositorio;
    }

    //Método para verificar usuario correcto

    public Boolean userOk (String user, String pswd){


        return true;
    }

}
