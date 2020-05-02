package com.example.signish.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.signish.R;
import com.example.signish.ViewModel.PicturesViewModel;

import static android.app.Activity.RESULT_OK;

public class Pictures extends Fragment {

    private PicturesViewModel mViewModel;

    Button subirFoto;
    Button cambiarFoto;

    public static Pictures newInstance() {
        return new Pictures();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(PicturesViewModel.class);

        View pictures_layout = inflater.inflate(R.layout.pictures_fragment, container, false);

        subirFoto = pictures_layout.findViewById(R.id.bttnSubir);
        cambiarFoto = pictures_layout.findViewById(R.id.bttnCambiar);

        subirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cargar_galeria();

            }
        });



        return pictures_layout;
    }

    private void cargar_galeria(){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,10);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PicturesViewModel.class);
        // TODO: Use the ViewModel
    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = null;

        if(requestCode == 10 && resultCode == RESULT_OK){

            Uri uri;
            uri = data.getData();

            try{

                bitmap = MediaStore.Images.Media
                        .getBitmap(getContentResolver(), uri);


            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (requestCode == 20 && resultCode == RESULT_OK){

            bitmap = (Bitmap) data.getExtras().get("data");

        }

        if(bitmap != null){
            imagen.setImageBitmap(bitmap);
        }

    }*/

}
