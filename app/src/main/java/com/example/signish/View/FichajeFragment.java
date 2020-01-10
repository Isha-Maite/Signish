package com.example.signish.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.signish.MainActivity;
import com.example.signish.ViewModel.FichajeViewModel;
import com.example.signish.R;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FichajeFragment extends Fragment {

    private FichajeViewModel mViewModel;
    Button entryButton;
    Button exitButton;
    Button loadRegistry;
    //TextView chnageText;
    Button internetButton;

    Button botonFichajes;

    String variableDatosGoogle ="";

    public static FichajeFragment newInstance() {
        return new FichajeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mViewModel =
                ViewModelProviders.of(this).get(FichajeViewModel.class);
        View ui_layout = inflater.inflate(R.layout.fichaje__fragment, container, false);

        mViewModel.setContext(getContext());
        entryButton = ui_layout.findViewById(R.id.button);
        exitButton = ui_layout.findViewById(R.id.button2);
        loadRegistry = ui_layout.findViewById(R.id.button3);
        //chnageText = ui_layout.findViewById(R.id.textView2);

        botonFichajes = ui_layout.findViewById(R.id.verFichajes);
        internetButton = ui_layout.findViewById(R.id.BttInternet);


        botonFichajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fm = new Listado();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fm);
                transaction.commit();
            }
        });



        entryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Entry Button Pressed");
                mViewModel.createEntry();

            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Create Button Pressed");
                mViewModel.createEntry();

            }
        });

        loadRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Exit Button Pressed");
                try {
                    mViewModel.readEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //Pruebas aquí para conexión a página web
        internetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Hilo hilo1 = new Hilo();
                hilo1.execute("http://www.google.es");
            }
        });


        return ui_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FichajeViewModel.class);
        // TODO: Use the ViewModel
    }


    public class Hilo extends AsyncTask<String,Void,String> {

        /*Esta función recibe una cadena con la direccion URL y devuelve una cadena con el
        contenido de la Web indicada. En caso de error se devolverá null. */

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection connection;
            URL ur1;
            connection = null;
            String result;
            result="";


            try{
                ur1 = new URL(strings[0]);
                connection = (HttpURLConnection) ur1.openConnection();


                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    StringBuilder sb = new StringBuilder();
                    InputStreamReader is = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(is);
                    String linea;
                    while((linea = reader.readLine()) != null){
                        sb.append(linea);
                    }

                    result = sb.toString();
                    reader.close();

                }

            }catch (Exception e){

            }
            //Se cierra siempre la conexión HTTP.
            finally {
                if(connection != null){
                    connection.disconnect();
                }
            }

            return result;
        }

        //Definir qué hacer con la return del hilo
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            variableDatosGoogle = s;
            //crear Bundle para pasar datos al fragment
            Bundle enviarDatos = new Bundle();
            enviarDatos.putString("parametro", variableDatosGoogle);
            Fragment fm = new InternetView();
            fm.setArguments(enviarDatos);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fm);
            transaction.commit();

        }
    }

}
