package com.example.signish.ViewModel;

import android.content.Context;

import com.example.signish.Model.Mensaje;
import com.example.signish.Repository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeedbackMessagesViewModel extends ViewModel {
    Repository miRepositorio = Repository.get();

    private MutableLiveData<String> mText;

    public FeedbackMessagesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public void setContext(Context context) {
        miRepositorio.setContext(context);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void WriteMsgFirebase(String msg){
        miRepositorio.WriteMsgFirebase(msg);
    }
}
