package com.example.signish.ViewModel;

import com.example.signish.Model.Mensaje;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecieveFeedbackViewModel extends ViewModel {
    private MutableLiveData<Mensaje> mMsg;
    private MutableLiveData<String> msgID;

    public RecieveFeedbackViewModel() {
        mMsg = new MutableLiveData<>();
        msgID = new MutableLiveData<>();

    }

    public LiveData<Mensaje> getMsg() {
        return mMsg;
    }

    public LiveData<String> getMsgID() {
        return msgID;
    }

    public void readMsgID(String uid){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("feedbackMessages").child("usuarios").child(uid).child("mensaje").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                msgID.postValue(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void readMsg(String msgID){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("feedbackMessages").child("mensajes").child(msgID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mMsg.postValue(dataSnapshot.getValue(Mensaje.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
