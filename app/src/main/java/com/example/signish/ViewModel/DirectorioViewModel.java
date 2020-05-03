package com.example.signish.ViewModel;

import com.example.signish.Model.User;
import com.example.signish.Repository;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class DirectorioViewModel extends ViewModel {

    Repository repository;
    ArrayList<User> lista_user_dep;
    //para llamar del vm al fragment hay que tener un liveData q pase la info
    MutableLiveData<ArrayList<User>> liveData_lista;


    public DirectorioViewModel(){

        repository = Repository.getRepository();
        lista_user_dep = new ArrayList<>();
        liveData_lista = new MutableLiveData<>();


    }

    //tiene que llamar a un método del repositorio
    public void getContactosDepartamento(String departamento){

        repository.getContactosDepartamento(departamento);
        repository.getListaUserDep().observeForever(new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                //guarda info array y livedata
                lista_user_dep = users;
                liveData_lista.postValue(lista_user_dep);
            }
        });
    }

    public LiveData<ArrayList<User>> getListaUser(){
        return liveData_lista;
    }

    public User getContactPosition(int posicion){

        return lista_user_dep.get(posicion);
    }

}

