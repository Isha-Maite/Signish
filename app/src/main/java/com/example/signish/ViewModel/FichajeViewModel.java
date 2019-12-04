package com.example.signish.ViewModel;
import android.content.Context;

import com.example.signish.Repository;

import java.io.IOException;

import androidx.lifecycle.ViewModel;


public class FichajeViewModel extends ViewModel {

    Repository miRepositorio = Repository.get();


    public void setContext(Context context){
        miRepositorio.setContext(context);
    }

    public Boolean createEntry() {
        return miRepositorio.createEntry();
    }

    public Boolean readEntry() throws IOException, ClassNotFoundException {
        return miRepositorio.readEntry();

    }




}
