package com.example.antonio.puzzle;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

//Clase responsable de iniciar el juego

public class MainActivity extends AppCompatActivity {


    public static AssetManager assets;
    private Button jugar;

    public void ExitButton (View V){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assets = getAssets();
        setContentView(R.layout.activity_main);



        jugar=(Button)findViewById(R.id.btnjugar);

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent play = new Intent(MainActivity.this, Play.class);
            startActivity(play);
        };
    });


    }
}
