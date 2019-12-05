package com.example.signish.Vista;

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

import com.example.signish.Modelo.Usuario;
import com.example.signish.R;
import com.example.signish.VistaModelo.ListadoViewModel;

import java.util.List;


public class listado extends Fragment {

    //declarar variables, luego se le asignara el id

    RecyclerView recyclerView;

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

        return interfazListado;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListadoViewModel.class);
        // TODO: Use the ViewModel
    }

    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

        // lista con los fichajes
        List<Usuario> fichajes;

        public UserAdapter(List<Usuario> fichajes) {
            this.fichajes = fichajes;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class UserViewHolder extends RecyclerView.ViewHolder{

            //declarar variables

            public UserViewHolder(@NonNull View itemView) {
                super(itemView);

                //aquí poner lo que se tiene que mostrar (variable/id)
            }
        }
    }

}
