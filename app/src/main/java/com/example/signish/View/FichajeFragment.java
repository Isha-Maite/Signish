package com.example.signish.View;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.signish.ViewModel.FichajeViewModel;
import com.example.signish.R;

public class FichajeFragment extends Fragment {

    private FichajeViewModel mViewModel;
    Button entradaSalida;

    public static FichajeFragment newInstance() {
        return new FichajeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View ui_layout = inflater.inflate(R.layout.fichaje__fragment, container, false);

        entradaSalida = ui_layout.findViewById(R.id.button);

        return ui_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FichajeViewModel.class);
        // TODO: Use the ViewModel
    }

}
