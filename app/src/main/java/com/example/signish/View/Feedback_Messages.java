package com.example.signish.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.signish.R;
import com.example.signish.ViewModel.FeedbackMessagesViewModel;

public class Feedback_Messages extends Fragment {

    private FeedbackMessagesViewModel mViewModel;
    TextView message;
    Button sendMessage;

    public static Feedback_Messages newInstance() {
        return new Feedback_Messages();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(FeedbackMessagesViewModel.class);

        View ui_layout = inflater.inflate(R.layout.feedback__messages_fragment, container, false);

        mViewModel.setContext(getContext());
        message = ui_layout.findViewById(R.id.messageFirebase);
        sendMessage = ui_layout.findViewById(R.id.sendMessage);


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("Message sent!!");
            }
        });

        return ui_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FeedbackMessagesViewModel.class);
        // TODO: Use the ViewModel
    }

}
