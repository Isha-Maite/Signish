package com.example.signish.ViewModel;
import android.content.Context;

import com.example.signish.Repository;
import androidx.lifecycle.ViewModel;


public class FichajeViewModel extends ViewModel {

    Repository miRepositorio = Repository.get();


    public void setContext(Context context){
        miRepositorio.setContext(context);
    }

    public Boolean createEntry() {
        return miRepositorio.createEntry();
    }




}
