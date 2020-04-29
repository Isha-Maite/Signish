package com.example.signish.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.signish.Model.User;
import com.example.signish.R;
import com.example.signish.ViewModel.DirectorioViewModel;

import java.util.ArrayList;

public class DirectorioFrag extends Fragment {

    private DirectorioViewModel mViewModel;

    private RecyclerView miRecycler;
    //TODO ARREGLAR VARIABLE
    //private AdapterUser miAdapter;
    private ArrayList<User> listado_users = new ArrayList<>();



    public static DirectorioFrag newInstance() {
        return new DirectorioFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Spinner spinner;

        mViewModel = ViewModelProviders.of(this).get(DirectorioViewModel.class);

        View ui_layout = inflater.inflate(R.layout.directorio_fragment, container, false);

        //TODO hacer recyclerView

        spinner = ui_layout.findViewById(R.id.spinner);

        //TODO REVISAR SPINNER
       /* // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);*/

        //Listener para comunicar con viewholder
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //qué hacer cuando hay algo seleccionado

            //adapterView tiene los textos, position indica cuál se ha seleccionado
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //mViewModel.getContactosDepartamento(parent.getItemIdAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //TODO RECYCLER
        /*mViewModel.getListaUser().observe(getViewLifecycleOwner(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                //cuando se modifique la lista de contactos, se actualice arraylist que utiliza el adaptador
                listado_users = users;
                miAdapter.notifyDataSetChanged();
            }
        });*/


        return ui_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DirectorioViewModel.class);
        // TODO: Use the ViewModel
    }

}
