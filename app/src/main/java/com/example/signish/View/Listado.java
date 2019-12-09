package com.example.signish.View;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.signish.Model.Usuario;
import com.example.signish.R;
import com.example.signish.ViewModel.ListadoViewModel;

import java.util.ArrayList;
import java.util.List;


public class Listado extends Fragment {

    //declarar variables, luego se le asignara el id

    RecyclerView recyclerView;

    //declarar variable del fichero vistamodelo
    ListadoViewModel listadoVistaModelo;

    private ListadoViewModel mViewModel;

    UserAdapter userAdapter;

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

        //carga de los usuarios para probar:
        Usuario user1 = new Usuario ("Maitechu", "1234");
        Usuario user2 = new Usuario ("Isha", "5678");
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        listaUsuarios.add(user1);
        listaUsuarios.add(user2);
        Log.i("prueba","crear usuarios y array");



        //El adapter es el traductor que recibe datos y los pinta en la pantalla
        userAdapter = new UserAdapter(listaUsuarios);

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
        List<Usuario> fichajes;


        public UserAdapter(List<Usuario> fichajes) {
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
            Usuario usuario_fichado = fichajes.get(position);

            holder.usuario.setText(usuario_fichado.getUsuario());

        }

        @Override
        //Sobreescribir para poder ver el tamaño de la array
        public int getItemCount() {
            return fichajes.size();
        }

        public class UserViewHolder extends RecyclerView.ViewHolder{

            //declarar variables
            TextView usuario;

            public UserViewHolder(@NonNull View itemView) {
                super(itemView);

                //aquí poner lo que se tiene que mostrar (variable/id)

                usuario = itemView.findViewById(R.id.TextFichaje);
            }
        }
    }

}
