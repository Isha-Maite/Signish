package com.example.signish.Model;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*Esta anotación hace referencia a que es una instancia de esta clase lo que vamos a
        guardar en la base de datos. No es obligatorio especificar el nombre de la tabla
        de la base de datos; de manera predeterminada recibe el nombre de la clase*/

@Entity(tableName = "Marcatge")

public class RoomFicha {
    //Identifica a cada objeto y no puede ser null.
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int mId;

    //Esta anotación se utiliza para definir una columna
    @ColumnInfo(name = "contenidoE")
    private String mMarcatgeEntrada;

    @ColumnInfo(name = "contenidoS")
    private String mMarcatgeSalida;

    @ColumnInfo(name="usuario")
    private String mUsuario;

    public RoomFicha() {
        mUsuario = "Maite";
    }

    @NonNull
    public int getId() {
        return mId;
    }

    public void setId(@NonNull int id) {
        mId = id;
    }

    public String getMarcatgeEntrada() {
        return mMarcatgeEntrada;
    }

    public void setMarcatgeEntrada(String marcatge) {
        mMarcatgeEntrada = marcatge;
    }

    public String getMarcatgeSalida() {
        return mMarcatgeSalida;
    }

    public void setMarcatgeSalida(String marcatge) {
        mMarcatgeSalida = marcatge;
    }

    public String getUsuario(){ return mUsuario; }

    public void setUsuario(String usuario){ mUsuario = usuario; }

}
