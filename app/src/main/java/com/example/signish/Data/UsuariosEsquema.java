package com.example.signish.Data;

import android.provider.BaseColumns;

public class UsuariosEsquema {

    //Se crea clase interna para guardar el nombre de las columnas de la tabla.
    //BaseColums añade una columna extra
    public static abstract class UsuariosEntrada implements BaseColumns{
        public static final String TABLE_NAME ="usuarios";

        public static final String ID = "id";
        public static final String DNI = "dni";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String CONTRASENYA = "contrasenya";
        public static final String HIJOS = "hijos";
        public static final String DISCAPACIDAD = "discapacidad";
        public static final String ROL = "rol";
        public static final String JORNADA = "jornada";


    }
}
