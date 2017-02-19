package com.example.antonio.puzzle;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;


//Esta clase contiene el menu de la aplicación.

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";
    Button empezar, logout, nivel, opciones;
    TextView nombre_usuario;
    Level registros = new Level();
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w(TAG, "activity_main");

        /*Relacionamos los botones del codigo java con los de
        la vista xml*/

        nombre_usuario = (TextView) findViewById(R.id.nombre);
        empezar=(Button)findViewById(R.id.button_start);
        logout = (Button) findViewById(R.id.button_logout);
        nivel = (Button) findViewById(R.id.button_nivel);
        opciones = (Button) findViewById(R.id.button_opciones);

        empezar.setOnClickListener(this);   //activamos todos los botones de la pantalla
        nivel.setOnClickListener(this);
        logout.setOnClickListener(this);
        opciones.setOnClickListener(this);



        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        username = registros.getUsername();
        nombre_usuario.setText(username);

        registros.setUsername(username); // guardamos nombre de usuario y contraseña
        registros.setPassword(password);

    }




    @Override
    public void onClick(View v) {
        final MediaPlayer click = MediaPlayer.create(this, R.raw.click);


        switch (v.getId()) {
            case R.id.button_start: //iniciamos primer puzle
                click.start();
                setContentView(new Screen_1(getApplicationContext(), MainActivity.this));
                break;

            case R.id.button_nivel: //Seleccion de nivel
                click.start();
                startActivity(new Intent(this, Choose_Game.class));
                break;

            case R.id.button_opciones:  //Abrimos submenu opciones
                click.start();
                startActivity(new Intent(this, Opciones.class));
                break;


            case R.id.button_logout:    //Logout de la aplicacion

                showMessage();
                //click.start();
                //startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    public void showMessage(){

        AlertDialog.Builder myMessage = new AlertDialog.Builder(this);

        myMessage.setTitle("Cerrar Sesión")

                .setPositiveButton("Si", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        setContentView(R.layout.login);
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                })

                .setIcon(R.drawable.alert)
                .create()
                .show();
    }


}



