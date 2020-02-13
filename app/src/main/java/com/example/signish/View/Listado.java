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

import com.example.signish.Data.FichajeLab;
import com.example.signish.R;
import com.example.signish.Model.RoomFicha;
import com.example.signish.ViewModel.ListadoViewModel;

import java.util.List;


public class Listado extends Fragment {

    //declarar variables, luego se le asignara el id

    RecyclerView recyclerView;

    //declarar variable del fichero vistamodelo
    ListadoViewModel listadoVistaModelo;

    private ListadoViewModel mViewModel;

    UserAdapter userAdapter;
    private FichajeLab mFichajeLab;

    public Listado() {
    }

    public static Listado newInstance() {
        return new Listado();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Le decimos el fragment que tiene que utilizar
        View interfazListado = inflater.inflate(R.layout.listado_fragment, container, false);

        //noName es el "recyclerView" añadido en el xml del fragment, el espacio donde
        //se mostrará la info que se quiera listar
        recyclerView = interfazListado.findViewById(R.id.noName);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mFichajeLab = FichajeLab.get(getContext());
        List<RoomFicha> listadoMarcatges = mFichajeLab.getMarcatgeEntrada();
        //El adapter es el traductor que recibe datos y los pinta en la pantalla
        userAdapter = new UserAdapter(listadoMarcatges);

        //añadir el adapter al recyclerView. Se genera como clase interna
        recyclerView.setAdapter(userAdapter);

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
        List<RoomFicha> fichajes;


        public UserAdapter(List<RoomFicha> fichajes) {
            this.fichajes = fichajes;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.viewholder,parent,false);
            return new UserViewHolder(view);
        }

        //Se encarga de pintar los datos
        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            RoomFicha usuario_fichado = fichajes.get(position);
            if (usuario_fichado == null) {
                return;
            }

            holder.usuario.setText(usuario_fichado.getUsuario());
            if (usuario_fichado.getMarcatgeEntrada() != null) {
                holder.fichaje.setText("Entrada: " + usuario_fichado.getMarcatgeEntrada());
            } else {
                holder.fichaje.setText("Salida: " + usuario_fichado.getMarcatgeSalida());
            }
        }

        @Override
        //Sobreescribir para poder ver el tamaño de la array
        public int getItemCount() {
            return fichajes.size();
        }

        public class UserViewHolder extends RecyclerView.ViewHolder{

            //declarar variables
            TextView usuario;
            TextView fichaje;

            public UserViewHolder(@NonNull View itemView) {
                super(itemView);

                //aquí poner lo que se tiene que mostrar (variable/id)

                usuario = itemView.findViewById(R.id.TextFichajeUsuario);
                fichaje = itemView.findViewById(R.id.TextFichaje);
            }
        }
    }

}
