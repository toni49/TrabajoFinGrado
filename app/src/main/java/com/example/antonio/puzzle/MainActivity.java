package com.example.antonio.puzzle;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.util.Log;
import android.widget.TextView;


//Clase responsable de iniciar el juego

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";
    Button empezar, logout;
    TextView nombre_usuario;
    UserLocalStore userLocalStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //assets = getAssets();
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Log.w(TAG, "activity_main");

        nombre_usuario = (TextView) findViewById(R.id.nombre);


        empezar=(Button)findViewById(R.id.button_start);
        logout = (Button) findViewById(R.id.button_logout);

        empezar.setOnClickListener(this);
        logout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
        /*{
            @Override
            public void onClick(View v) {
                setContentView(new Screen_ima(getApplicationContext(), MainActivity.this));
               // setContentView(R.layout.signup);
                // Dibujamos todas las imagenes del nivel con este ContentView.
               // Intent play = new Intent(MainActivity.this, Play.class);
                //startActivity(play);
        };
    });*/


    }

    @Override
    protected void onStart(){
        super.onStart();

        if(authenticate() == true){
            MostrarUsuario();
        }
    }

    private void MostrarUsuario(){
        User user = userLocalStore.getLoggedInUser();

        nombre_usuario.setText(user.username);
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:
                setContentView(new Screen_ima(getApplicationContext(), MainActivity.this));
                break;

            case R.id.button_logout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}
