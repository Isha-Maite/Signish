package com.example.signish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.os.Bundle;


import com.example.signish.Model.Fichaje;
import com.example.signish.Model.Usuario;
import com.example.signish.View.LoginFragment;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragManager;
    Repository miRepositorio = Repository.get();

    private static final String FILE_NAME = "userFile.dat";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciar el fragment
        fragManager = getSupportFragmentManager();

        //usar replace porque add unicamente es para cuando esta vacio
        //los argumentos son el container donde quiero poner el fragment y el fragment
        fragManager.beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();

        createEntry();
    }

    public void createEntry() {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
            ObjectOutputStream os = new ObjectOutputStream(fos);

            Usuario usuario = new Usuario("Maite", "1234");
            os.writeObject(usuario);
            os.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
