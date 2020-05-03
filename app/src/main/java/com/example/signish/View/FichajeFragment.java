package com.example.signish.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.signish.Data.FichajeLab;
import com.example.signish.MainActivity;
import com.example.signish.Model.RoomFicha;
import com.example.signish.ViewModel.FichajeViewModel;
import com.example.signish.R;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class FichajeFragment extends Fragment {

    private FichajeViewModel mViewModel;
    Button entryButton;
    Button exitButton;
    //TextView chnageText;
    Button internetButton;
    MainActivity mainActivity;

    Button botonFichajes;
    private Menu Menu;

    private FichajeLab mFichajeLab;
    private RoomFicha mFicha;

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
        botonFichajes = ui_layout.findViewById(R.id.verFichajes);



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
                System.out.println(" \n Entry Button Pressed \n");
                //mViewModel.createEntry();
                mViewModel.createEntradaFichaje();

                String accion= "entrada";
                guardar(accion);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("\n Exit Button Pressed \n");
                mViewModel.createSalidaFichaje();
                //mViewModel.createEntry();

                String accion= "salida";
                System.out.println("\n Entry Button Pressed \n");
                guardar(accion);
            }
        });


        mainActivity = (MainActivity) getActivity();
        mainActivity.makeMenuVisible();
        return ui_layout;
    }


    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FichajeViewModel.class);
        // TODO: Use the ViewModel
    }

    public void guardar(String accion){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateFicha = fmt.format(Calendar.getInstance().getTime());
        mFichajeLab = FichajeLab.get(getContext());

        if(mFicha == null){
            mFicha = new RoomFicha();
            if(accion == "entrada"){
                mFicha.setMarcatgeEntrada(dateFicha);
                mFichajeLab.addMarcatge(mFicha);
            } else {
                mFicha.setMarcatgeSalida(dateFicha);
                mFichajeLab.addMarcatge(mFicha);
            }

            Toast.makeText(getContext(),"Fichaje OK",Toast.LENGTH_SHORT).show();
        } else {
            if(accion == "entrada"){
                mFicha.setMarcatgeEntrada(dateFicha);
                mFichajeLab.addMarcatge(mFicha);
            } else {
                mFicha.setMarcatgeSalida(dateFicha);
                mFichajeLab.addMarcatge(mFicha);
            }
            Toast.makeText(getContext(),"Fichaje OK else",Toast.LENGTH_SHORT).show();
        }
        mFicha.setMarcatgeEntrada(null);
        mFicha.setMarcatgeSalida(null);
    }


    }


