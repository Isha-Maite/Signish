package com.example.signish.View;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.signish.Model.Fichaje;
import com.example.signish.Model.Usuario;
import com.example.signish.R;
import com.example.signish.ViewModel.ListadoViewModel;

import java.util.List;


public class listado extends Fragment {

    //declarar variables, luego se le asignara el id

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    //declarar variable del fichero vistamodelo
    ListadoViewModel listadoVistaModelo;

    private ListadoViewModel mViewModel;

    public static listado newInstance() {
        return new listado();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //declarar variable y pegar el return
        View interfazListado = inflater.inflate(R.layout.listado_fragment, container, false);
        recyclerView = interfazListado.findViewById(R.id.noName);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerView.setAdapter(mAdapter);

        return interfazListado;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListadoViewModel.class);
        // TODO: Use the ViewModel
    }

    //hola qué es un adapter gracias
    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {


        List<Fichaje> listaFichaje;

        public UserAdapter(List<Fichaje> listaFichaje){
            this.listaFichaje = listaFichaje;

        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            LayoutInflater layoutInflater = getLayoutInflater();

            return new UserViewHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int i) {
            Fichaje f = listaFichaje.get(i);
            holder.textHola.setText(f.getCurrentTime());

        }

        @Override
        public int getItemCount() {

            return listaFichaje.size();

        }

        //subclase para ViewHolder

        public class UserViewHolder extends RecyclerView.ViewHolder{

            //declarar variables
            public TextView textHola;


           public UserViewHolder(LayoutInflater layoutInflater,ViewGroup parent) {

               super(layoutInflater.inflate(R.layout.viewholder,parent,false));

               //aquí poner lo que se tiene que mostrar (variable/id)
               textHola = itemView.findViewById(R.id.TextFichaje);
           }

        }
    }

}
