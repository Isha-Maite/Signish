package com.example.signish.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.signish.R;
import com.example.signish.ViewModel.LoginVistaModelo;

import java.io.IOException;

public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //declarar variables, luego se le asignara el id
    EditText textUser, textPass;
    Button botonEntrar;
    TextView textoOk;

    //declarar variable del fichero vistamodelo
    LoginVistaModelo loginVistaModelo;
    FragmentManager fragManager;

    public LoginFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        loginVistaModelo = ViewModelProviders.of(this).get(LoginVistaModelo.class);
        loginVistaModelo.setContext(getContext());

        // Inflate the layout for this fragment
        //declarar variable y pegar el return
        View interfazLogin = inflater.inflate(R.layout.fragment_login, container, false);

        //ahora identificar las variables con el id de los elementos


        textUser = interfazLogin.findViewById(R.id.textUser);
        textPass = interfazLogin.findViewById(R.id.textPass);
        botonEntrar = interfazLogin.findViewById(R.id.botonEntrar);


        botonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    if (loginVistaModelo.userOk(textUser.getText().toString(), textPass.getText().toString())) {
                        Fragment fm = new FichajeFragment();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, fm);
                        transaction.commit();

                    } else {
                        Toast.makeText(getContext(), "ERROR!! Wrong Password.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

        return interfazLogin;
    }

}
