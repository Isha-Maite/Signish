package com.example.signish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.signish.Vista.LoginFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciar el fragment
        fragManager = getSupportFragmentManager();

        //usar replace porque add unicamente es para cuando esta vacio
        fragManager.beginTransaction().replace(R.id.fragmentLogin,new LoginFragment()).commit();

    }
}
