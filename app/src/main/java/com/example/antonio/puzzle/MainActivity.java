package com.example.antonio.puzzle;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.util.Log;


//Clase responsable de iniciar el juego

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    public static AssetManager assets;
    private Button jugar;
    Ini_screen fin;


    public void ExitButton (View V){

        android.os.Process.killProcess(android.os.Process.myPid());
        finish();
        System.exit(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //assets = getAssets();
        setContentView(R.layout.activity_main);
        Log.w(TAG, "activity_main");


        jugar=(Button)findViewById(R.id.btnjugar);

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(new Screen_3(getApplicationContext(), MainActivity.this));
                // Dibujamos todas las imagenes del nivel con este ContentView.
               // Intent play = new Intent(MainActivity.this, Play.class);
                //startActivity(play);
        };
    });


    }
}
