package com.example.antonio.puzzle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by antonio on 11/6/16.
 */
public class Opciones extends AppCompatActivity implements View.OnClickListener{


    Button fondo, record, measures, back, instrucciones;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones);


        fondo = (Button) findViewById(R.id.button_fondo);
        record = (Button) findViewById(R.id.button_record);
        measures = (Button) findViewById(R.id.button_velocity_Measures);
        instrucciones = (Button) findViewById(R.id.button_instrucciones);

        back = (Button) findViewById(R.id.button_back);


        fondo.setOnClickListener(this);
        record.setOnClickListener(this);
        measures.setOnClickListener(this);
        instrucciones.setOnClickListener(this);
        back.setOnClickListener(this);
    }


        @Override
        public void onClick(View v) {
            final MediaPlayer click = MediaPlayer.create(this, R.raw.click);

            switch (v.getId()) {
                case R.id.button_fondo:
                    click.start();
                    startActivity(new Intent(this, Select_Background.class));
                    break;

                case R.id.button_record:
                    click.start();
                    startActivity(new Intent(this, AudioRecord.class));
                    break;

                case R.id.button_velocity_Measures:
                    click.start();
                    startActivity(new Intent(this, pruebas_vel.class));
                    break;

                case R.id.button_instrucciones:  //Abrimos submenu opciones
                    click.start();
                    startActivity(new Intent(this, Instructions.class));
                    break;

                case R.id.button_back:
                    click.start();
                    startActivity(new Intent(this, MainActivity.class));
                    break;


            }
        }
}
