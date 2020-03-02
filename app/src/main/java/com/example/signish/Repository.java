package com.example.signish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.signish.Data.FichajeEsquema;
import com.example.signish.Model.Fichaje;
import com.example.signish.Model.Mensaje;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Repository {

    private static final String FILE_NAME = "entradaSalida.dat";
    private static Repository repositorio;
    private Context context;
    private File file = null;
//    private static Connection conn;

    String userSq;
    String passSq;


    //Se genera constructor en privado para que no se haga más de un repositorio
    private Repository() {
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }

    //el método get será el encargado de llamar al constructor una única vez
    public static Repository get() {
        if (repositorio == null) {
            repositorio = new Repository();
        }
        return repositorio;
    }


    //Método para verificar usuario correcto
    public Boolean userOk(String user, String password) {

        UsuarioDbHelper admin = new UsuarioDbHelper(context);
        SQLiteDatabase db = admin.getReadableDatabase();
        //se crea objeto cursor para guardar los datos de la consulta
        Cursor cursor = db.rawQuery("SELECT nombre, contrasenya FROM usuarios", null);

        //se recorren los datos con un while
        while(cursor.moveToNext()){
            userSq = cursor.getString(cursor.getColumnIndex("nombre"));
            passSq = cursor.getString(cursor.getColumnIndex("contrasenya"));
            if(user.equals(userSq)&&password.equals(passSq)){
                if (user.equals("Maite")&&password.equals("1234")){
                    Log.i("Admin", "Usuario es admin");
                } else {
                    Log.i("Worker", "Usuario no es admin");
                }


                cursor.close();
                return true;
            }
        } cursor.close();
          return false;


    }


    /*método para guardar fichajeList, modificado para que si el fichero existe no añada la cabecera,
    Una cabecera que se genera sola en el fichero.
    Se sobreescribe el método writeStreamHeader*/
    /*public void createEntry() {

        FichajeDbHelper admin = new FichajeDbHelper(context);

        SQLiteDatabase db = admin.getReadableDatabase();

        ContentValues values = new ContentValues();

        //values.put(FichajeEsquema.FichajeEntrada.ID, (byte[]) null);
        values.put(FichajeEsquema.FichajeEntrada.currentTime, Calendar.getInstance().getTime().toString());

        // Insertar...
        db.insert(FichajeEsquema.FichajeEntrada.TABLE_NAME, null, values);

        Log.i("Fichaje", "Ha creado Entrada");

    }*/

    public void guardar(){

        String marcatge = Calendar.getInstance().getTime().toString();


    }


    public void readEntry() throws IOException {

        FichajeDbHelper admin = new FichajeDbHelper(context);

        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT currenttime from fichajes", null);
        while(cursor.moveToNext()){
            String currentimeSql = cursor.getString(cursor.getColumnIndex("currentTime"));

            System.out.print("\n" + currentimeSql + "\n");
        } cursor.close();

    }

    public void WriteMsgFirebase(String msg){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference msg_reference = db.child("feedbackMessages").child("mensajes").push();
        msg_reference.setValue(new Mensaje("usuario inventado",
                "pVBEOgINbVOGdkne7Sjhg3ycW6h2", msg));
        db.child("feedbackMessages").child("usuarios").child("pVBEOgINbVOGdkne7Sjhg3ycW6h2").child("mensaje")
                .setValue(msg_reference.getKey());
    }

    List<Fichaje> fichajeList = new ArrayList<>();
    private static Fichaje selectedFichaje;

    String postgrs_select = "Select * from Fichaje";

    public Thread createEntryPostgres(){
        Thread entryPostgres = new Thread(){
            public void run(){
                Connection conn = null;
                try{
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.22/", "angela", "ruizrobles");

                    Statement st = conn.createStatement();
                    st.execute(CREATE_POSTGRES);
                    int row =  st.executeUpdate("INSERT INTO FICHAJE (entrada) " +
                            "VALUES ('" + currentTime() + "')");
                    System.out.println(row);

                    PreparedStatement preparedStatement = conn.prepareStatement(postgrs_select);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        String entrada = resultSet.getString("entrada");

                        Fichaje obj = new Fichaje();
                        obj.setEntrada(entrada);


                        fichajeList.add(obj);
                    }


                }catch (SQLException e){
                    Log.i("Exception", "----------- Ha Fallado SQL -----------------");
                }
                catch (ClassNotFoundException c){
                    Log.i("Exception", "----------- No se encuentra classe -----------------");

                }
            }

        };
        return entryPostgres;
    }


    public Thread createExitPostgres(){
        Thread exitPostgres = new Thread(){
            public void run(){
                Connection conn = null;
                try{
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.22/", "angela", "ruizrobles");

                    Statement st = conn.createStatement();
                    st.execute(CREATE_POSTGRES);
                    int row =  st.executeUpdate("INSERT INTO FICHAJE (salida) " +
                            "VALUES ('" + currentTime() + "')");
                    System.out.println(row);



                }catch (SQLException e){
                    Log.i("Exception", "----------- Ha Fallado SQL -----------------");
                }
                catch (ClassNotFoundException c){
                    Log.i("Exception", "----------- No se encuentra classe -----------------");

                }
            }

        };
        return exitPostgres;
    }

    public Thread updateTabla(){
        Thread exitPostgres = new Thread(){
            public void run(){
                Connection conn = null;
                try{
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.22/", "angela", "ruizrobles");

                    Statement st = conn.createStatement();
                    int row = st.executeUpdate("UPDATE FICHAJE SET ENTRADA='" + currentTime() + "' WHERE ENTRADA='" + selectedFichaje.getEntrada() + "'" );
                    System.out.println(row);



                }catch (SQLException e){
                    Log.i("Exception", "----------- Ha Fallado SQL -----------------");
                }
                catch (ClassNotFoundException c){
                    Log.i("Exception", "----------- No se encuentra classe -----------------");

                }
            }

        };
        return exitPostgres;
    }

    public Thread deleteTable(){
        Thread exitPostgres = new Thread(){
            public void run(){
                Connection conn = null;
                try{
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.22/", "angela", "ruizrobles");

                    Statement st = conn.createStatement();
                    int row = st.executeUpdate("DELETE FROM FICHAJE WHERE ENTRADA='" + selectedFichaje.getEntrada() + "'");



                }catch (SQLException e){
                    Log.i("Exception", "----------- Ha Fallado SQL -----------------");
                }
                catch (ClassNotFoundException c){
                    Log.i("Exception", "----------- No se encuentra classe -----------------");

                }
            }

        };
        return exitPostgres;
    }



    private static final String CREATE_POSTGRES = "CREATE TABLE IF NOT EXISTS FICHAJE"
            + "("
            +  "Entrada" + " TEXT,"
            +  "Salida TEXT,"
            + "UNIQUE (" + "Entrada" + "))";

    public void createFichajeEntrada(){
        createEntryPostgres().start();
    }

    public void createSalidaFichaje(){
        createExitPostgres().start();
    }

    public String currentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }

}

