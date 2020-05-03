package com.example.signish;

import android.app.Application;

public class DirectorioApp extends Application {

    //se llama por el sistema operativo cuando arranca la app
    public void onCreate(){
        super.onCreate();
            //cargar el repositorio y pasarle el contexto
            Repository.get(getApplicationContext());
    }

    public void onTerminate(){
        super.onTerminate();
    }
}
