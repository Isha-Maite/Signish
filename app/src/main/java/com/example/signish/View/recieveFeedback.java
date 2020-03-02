package com.example.signish.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.signish.Model.Mensaje;
import com.example.signish.ViewModel.RecieveFeedbackViewModel;
import com.example.signish.R;

public class recieveFeedback extends Fragment {

    private RecieveFeedbackViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel =  ViewModelProviders.of(this).get(RecieveFeedbackViewModel.class);
        View root = inflater.inflate(R.layout.recieve_feedback_fragment, container, false);

        final TextView from = root.findViewById(R.id.remiteTextView);
        final TextView msg = root.findViewById(R.id.receiveMsgTextView);

        mViewModel.readMsgID("pVBEOgINbVOGdkne7Sjhg3ycW6h2");

        mViewModel.getMsgID().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mViewModel.readMsg(s);
                Log.i("VIEWMODEL getMsgID", s);
            }
        });

        mViewModel.getMsg().observe(this, new Observer<Mensaje>() {
            @Override
            public void onChanged(Mensaje mensaje) {
                from.setText(mensaje.getEmisor());
                msg.setText(mensaje.getMensaje());
                Log.i("VIEWMODEL getMsg", mensaje.toString());
            }
        });

        return root;
    }
}
