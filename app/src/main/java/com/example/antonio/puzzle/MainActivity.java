package com.example.antonio.puzzle;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.util.Log;
import android.widget.TextView;


//Esta clase contiene el menu de la aplicación.

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";
    Button empezar, logout, nivel;
    TextView nombre_usuario;
    //UserLocalStore userLocalStore;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //final MediaPlayer correct = MediaPlayer.create(this, R.raw.correct);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Log.w(TAG, "activity_main");


        nombre_usuario = (TextView) findViewById(R.id.nombre);
        empezar=(Button)findViewById(R.id.button_start);
        logout = (Button) findViewById(R.id.button_logout);
        nivel = (Button) findViewById(R.id.button_nivel);

        empezar.setOnClickListener(this);
        nivel.setOnClickListener(this);
        logout.setOnClickListener(this);

        //userLocalStore = new UserLocalStore(this);

        Intent intent = getIntent();
        // String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        nombre_usuario.setText(username);

    }

   /* @Override
    protected void onStart(){
        super.onStart();

        if(authenticate() == true){
            MostrarUsuario();
        }
        else {
            startActivity(new Intent(MainActivity.this, Login.class));
        }

    }*/

    private void MostrarUsuario(){

       /* Intent intent = getIntent();
        // String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        nombre_usuario.setText(username);*/
        //User user = userLocalStore.getLoggedInUser();
        //nombre_usuario.setText(user.username);
    }

    /*private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }*/

    @Override
    public void onClick(View v) {
        final MediaPlayer click = MediaPlayer.create(this, R.raw.click);

        switch (v.getId()) {
            case R.id.button_start:
                click.start();
                setContentView(new Screen_1(getApplicationContext(), MainActivity.this)); //Iniciar primera pantalla del juego al pulsar el boton start.
                break;

            case R.id.button_nivel:
                click.start();
                startActivity(new Intent(this, Elegir_nivel.class));
                break;

            case R.id.button_logout:
                click.start();
                //userLocalStore.clearUserData();
                //userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, LoginActivity.class));   //Se realiza un logout volviendo a la pagina donde se solicitan los credenciales.
                break;
        }
    }


}
