package com.example.signish;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPostgres extends AsyncTask<Void,Integer,Boolean> {

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
           // String cadenaConexion = "jdbc:jtds:sqlserver://direccion_servidor;databaseName=nombre_db;USER=user;PASSWORD=pass";
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.0.22/", "angela", "ruizrobles");
            if (con == null)
            {
                Log.i("No funciona", "Postgres");
                return false;
            } else {
                Log.i("funciona", "Postgres");
            }
        } catch (NoClassDefFoundError e){
            Log.e("Definicion de clase",e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("Clase no encontrada",e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR Conexion:",e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean resultado) {
        if(resultado) {
            Log.e("LOG:", "conectado");
        }else {
            Log.e("LOG:", "no conectado");
        }
    }

}
