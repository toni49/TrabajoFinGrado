package com.example.antonio.puzzle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by antonio on 9/10/16.
 */
public class Elegir_nivel extends AppCompatActivity implements View.OnClickListener {

    Button nivel1, nivel2, nivel3;
    Level registro = new Level();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.elegir_nivel);

            nivel1 = (Button) findViewById(R.id.button_1);
            nivel2 = (Button) findViewById(R.id.button_2);
            nivel3 = (Button) findViewById(R.id.button_3);

            nivel1.setOnClickListener(this);
            nivel2.setOnClickListener(this);
            nivel3.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {

            final MediaPlayer click = MediaPlayer.create(this, R.raw.click);
            switch (v.getId()) {
                case R.id.button_1:
                    click.start();
                    setContentView(new Screen_1(getApplicationContext(), Elegir_nivel.this)); //Iniciar primera pantalla del juego al pulsar el boton start.
                    break;

                case R.id.button_2:
                    click.start();
                    setContentView(new Screen_2(getApplicationContext(), Elegir_nivel.this));
                    break;

                case R.id.button_3:
                    click.start();
                    setContentView(new Screen_ima3(getApplicationContext(), Elegir_nivel.this));
                    break;

                default:
                    break;
            }




        }
    }
