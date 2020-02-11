package com.example.signish.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.signish.Data.FichajeEsquema;
import com.example.signish.Data.FichajeLab;
import com.example.signish.Model.Fichaje;
import com.example.signish.RoomFicha;
import com.example.signish.ViewModel.FichajeViewModel;
import com.example.signish.R;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;


public class FichajeFragment extends Fragment {

    private FichajeViewModel mViewModel;
    Button entryButton;
    Button exitButton;
    Button loadRegistry;
    Button botonFichajes;

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
        loadRegistry = ui_layout.findViewById(R.id.button3);
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


                //mViewModel.guardar();

                mFichajeLab = FichajeLab.get(getContext());
                List<RoomFicha>marcatges = mFichajeLab.getMarcatges();

                guardar();

                //mViewModel.createEntry();

            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("\n Entry Button Pressed \n");
                //mViewModel.createEntry();

            }
        });

        loadRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("\n Read Button Pressed \n");
                try {
                    mViewModel.readEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

    public void guardar(){

        mFichajeLab.addMarcatge(mFicha);

    }


}
