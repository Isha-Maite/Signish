package com.example.signish;
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
    @PrimaryKey
    @NonNull
    private String mId;

    //Esta anotación se utiliza para definir una columna
    @ColumnInfo(name = "contenido")
    private String mMarcatge;

    public RoomFicha() {
        mId = "Maite";
    }

    @NonNull
    public String getId() {
        return mId;
    }

    public void setId(@NonNull String id) {
        mId = id;
    }

    public String getMarcatge() {
        return mMarcatge;
    }

    public void setMarcatge(String marcatge) {
        mMarcatge = marcatge;
    }
}
