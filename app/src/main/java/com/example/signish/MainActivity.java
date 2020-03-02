package com.example.signish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.signish.Model.Usuario;
import com.example.signish.View.recieveFeedback;
import com.example.signish.View.Feedback_Messages;
import com.example.signish.View.FichajeFragment;
import com.example.signish.View.InternetView;
import com.example.signish.View.LoginFragment;

import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragManager;
    Repository miRepositorio = Repository.get();
    String variableDatosGoogle ="";

    Menu menu;

    private static final String FILE_NAME = "userFile.dat";


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        this.menu = menu;


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.menu_home){
            fragManager = getSupportFragmentManager();
            fragManager.beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
        }

        if(id==R.id.menu_feedback){
            Toast.makeText(this,"FeedBack",Toast.LENGTH_SHORT).show();
            Fragment fm = new Feedback_Messages();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fm);
            transaction.commit();
        }

        if(id==R.id.menu_in){
            Toast.makeText(this,"Clock in",Toast.LENGTH_SHORT).show();
            Fragment fm = new FichajeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fm);
            transaction.commit();

        }

        if(id==R.id.recieveFeedback_menu){
            Toast.makeText(this,"Recieve Message",Toast.LENGTH_SHORT).show();
            Fragment fm = new recieveFeedback();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fm);
            transaction.commit();
        }


        if(id==R.id.menu_intranet){
            Toast.makeText(this,"Intranet",Toast.LENGTH_SHORT).show();

            Hilo hilo1 = new Hilo();
            hilo1.execute("https://agora.xtec.cat/insjoandaustria/moodle/login/index.php");
        }

        if(id==R.id.logout){
            Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show();
            Fragment fm = new LoginFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fm);
            transaction.commit();

            makeMenuInvisible();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciar el fragment
        fragManager = getSupportFragmentManager();

        //usar replace porque add unicamente es para cuando esta vacio
        //los argumentos son el container donde quiero poner el fragment y el fragment
        fragManager.beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();

        createEntry();
        UsuarioDbHelper admin = new UsuarioDbHelper(this);
    }


    public void createEntry() {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
            ObjectOutputStream os = new ObjectOutputStream(fos);

            Usuario usuario = new Usuario("Maite", "1234");
            os.writeObject(usuario);
            os.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public class Hilo extends AsyncTask<String,Void,String> {

        /*Esta función recibe una cadena con la direccion URL y devuelve una cadena con el
        contenido de la Web indicada. En caso de error se devolverá null. */

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection connection;
            URL ur1;
            connection = null;
            String result;
            result="";


            try{
                ur1 = new URL(strings[0]);
                connection = (HttpURLConnection) ur1.openConnection();


                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    StringBuilder sb = new StringBuilder();
                    InputStreamReader is = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(is);
                    String linea;
                    while((linea = reader.readLine()) != null){
                        sb.append(linea);
                    }

                    result = sb.toString();
                    reader.close();

                }

            }catch (Exception e){

            }
            //Se cierra siempre la conexión HTTP.
            finally {
                if(connection != null){
                    connection.disconnect();
                }
            }

            return result;
        }

        //Definir qué hacer con la return del hilo
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            variableDatosGoogle = s;
            //crear Bundle para pasar datos al fragment
            Bundle enviarDatos = new Bundle();
            enviarDatos.putString("parametro", variableDatosGoogle);
            Fragment fm = new InternetView();
            fm.setArguments(enviarDatos);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fm);
            transaction.commit();

        }
    }

    public void makeMenuInvisible(){
        menu.getItem(1).setVisible(false);
        menu.getItem(2).setVisible(false);
        menu.getItem(3).setVisible(false);
        menu.getItem(4).setVisible(false);
        menu.getItem(5).setVisible(false);
        menu.getItem(6).setVisible(false);
    }
    public void makeMenuVisible(){
        menu.getItem(1).setVisible(true);
        menu.getItem(2).setVisible(true);
        menu.getItem(3).setVisible(true);
        menu.getItem(4).setVisible(true);
        menu.getItem(5).setVisible(true);
        menu.getItem(6).setVisible(true);

    }
}
