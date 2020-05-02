package com.example.signish.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.signish.Model.User;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.signish.R;
import com.example.signish.ViewModel.DirectorioViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactoFragment extends Fragment {

    private DirectorioViewModel mViewModel;
    TextView nom, email, departamento;

    public ContactoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // ViewModel compartido
        mViewModel = new ViewModelProvider(requireActivity()).get(DirectorioViewModel.class);
        View view = inflater.inflate(R.layout.fragment_contacto, container, false);

        nom = view.findViewById(R.id.textViewnombre);
        email = view.findViewById(R.id.txtmail);
        departamento = view.findViewById(R.id.textViewDepart);

        //se coge la arraylist de directorioViewModel

        cargar_datos_contacto(mViewModel.getContactPosition(posicion));

        //para que el contacto llegue, el vM

        return view;
    }

    public void cargar_datos_contacto(User user){
        nom.setText(user.getNombre()+""+user.getApellido());
        email.setText(user.getEmail());
        departamento.setText("");
    }
}
