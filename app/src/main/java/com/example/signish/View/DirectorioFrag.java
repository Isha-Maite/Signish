package com.example.signish.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.signish.Model.User;
import com.example.signish.R;
import com.example.signish.ViewModel.DirectorioViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DirectorioFrag extends Fragment {

    //declarar variables, luego se le asignara el id
    private DirectorioViewModel mViewModel;

    private RecyclerView miRecycler;
    private UserAdapter userAdapter;
    ArrayList<User> listado_users = new ArrayList<>();
    public Context context;


    public static DirectorioFrag newInstance() {
        return new DirectorioFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Spinner spinner;

        //mViewModel = ViewModelProviders.of(this).get(DirectorioViewModel.class);
        //cuando queremoms compartir viewmodel para el lista detalle
        mViewModel = new ViewModelProvider(requireActivity()).get(DirectorioViewModel.class);

        //mViewModel = new ViewModelProviders.of(this).get(DirectorioViewModel.class);

        View interfazDep = inflater.inflate(R.layout.directorio_fragment, container, false);

        //TODO hacer recyclerView
        miRecycler = interfazDep.findViewById(R.id.recyclerView);
        miRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        miRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));

        userAdapter = new UserAdapter();
        //añadir el adapter al recyclerView. Se genera como clase interna
        miRecycler.setAdapter(userAdapter);

        spinner = interfazDep.findViewById(R.id.spinner);

       // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Listener para comunicar con viewholder
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //qué hacer cuando hay algo seleccionado

            //adapterView tiene los textos, position indica cuál se ha seleccionado
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                mViewModel.getContactosDepartamento(parent.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //TODO RECYCLER
        mViewModel.getListaUser().observe(getViewLifecycleOwner(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {

                //cuando se modifique la lista de contactos, se actualice arraylist que utiliza el adaptador
                listado_users = users;
                userAdapter.notifyDataSetChanged();
            }
        });


        return interfazDep;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DirectorioViewModel.class);
        // TODO: Use the ViewModel
    }

    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.viewholder_dep, parent, false);
            return new UserViewHolder(view);
        }

        //se encarga de pintar los datos
        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            //revisar si falla Recycler

        }

        @Override
        public int getItemCount() {
            return listado_users.size();
        }

        public class UserViewHolder extends RecyclerView.ViewHolder{

            //declarar variables
            TextView nombre;
            TextView apellido;
            TextView email;

            public UserViewHolder(@NonNull View itemView) {
                super(itemView);

                //aquí poner lo que se tiene que mostrar (variable/id)

                nombre = itemView.findViewById(R.id.txtName);
                apellido = itemView.findViewById(R.id.txtSur);
                email = itemView.findViewById(R.id.txtmail);

                //Listener sobre el item pulsado para contactoFragment
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //no tenemos navigation, WTF
                    }
                }


            }

            public void rellenarContacto (User user){
                nombre.setText(user.getNombre());
                apellido.setText(user.getApellido());
                email.setText(user.getEmail());
            }
        }
    }






}
