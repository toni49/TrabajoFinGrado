package com.example.antonio.puzzle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by antonio on 11/13/16.
 */
public class Juegos_2 extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn_1, btn_2, btn_3, btn_4, flechaL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juegos2);

        final MediaPlayer correct = MediaPlayer.create(this, R.raw.correct);

        btn_1 = (ImageButton) findViewById(R.id.imageButton7);
        btn_2 = (ImageButton) findViewById(R.id.imageButton8);
        btn_3 = (ImageButton) findViewById(R.id.imageButton9);
        btn_4 = (ImageButton) findViewById(R.id.imageButton10);
        flechaL = (ImageButton) findViewById(R.id.imageLeft);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        flechaL.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        final MediaPlayer click = MediaPlayer.create(this, R.raw.click);
        switch(v.getId()){
            case R.id.imageButton7:
                click.start();
                setContentView(new Screen_ima(getApplicationContext(), Juegos_2.this));
                break;

            case R.id.imageButton8:
                click.start();
                setContentView(new Screen_ima2(getApplicationContext(), Juegos_2.this));
                break;

            case R.id.imageButton9:
                click.start();
                setContentView(new Screen_ima3(getApplicationContext(), Juegos_2.this));
                break;

            case R.id.imageButton10:
                click.start();
                setContentView(new Screen_ima4(getApplicationContext(), Juegos_2.this));
                break;

            case R.id.imageLeft:
                click.start();
                startActivity(new Intent(this, Juegos.class));
                break;

            default:
                break;
        }
    }
}


