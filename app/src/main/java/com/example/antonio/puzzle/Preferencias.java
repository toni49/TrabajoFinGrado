package com.example.antonio.puzzle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by antonio on 11/6/16.
 */
public class Preferencias extends AppCompatActivity implements View.OnClickListener{


    Button fondo, record;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferencias);


        fondo = (Button) findViewById(R.id.button_fondo);
        record = (Button) findViewById(R.id.button_record);


        fondo.setOnClickListener(this);
        record.setOnClickListener(this);
    }


        @Override
        public void onClick(View v) {
            final MediaPlayer click = MediaPlayer.create(this, R.raw.click);

            switch (v.getId()) {
                case R.id.button_fondo:
                    click.start();
                    startActivity(new Intent(this, Fondo.class));
                    break;

                case R.id.button_record:
                    click.start();
                    startActivity(new Intent(this, AudioRecordTest.class));
                    break;


            }
        }
}
