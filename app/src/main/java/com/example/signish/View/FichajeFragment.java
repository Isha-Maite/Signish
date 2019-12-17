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
import android.widget.TextView;
import android.widget.Toast;

import com.example.signish.MainActivity;
import com.example.signish.ViewModel.FichajeViewModel;
import com.example.signish.R;

import java.io.IOException;

public class FichajeFragment extends Fragment {

    private FichajeViewModel mViewModel;
    Button entryButton;
    Button exitButton;
    Button loadRegistry;
    //TextView chnageText;
    Button internetButton;

    Button botonFichajes;

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
                try{
                    Thread.sleep(10000);

                } catch (InterruptedException e) {
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

}
