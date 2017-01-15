package com.example.antonio.puzzle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by antonio on 11/9/16.
 */
public class Juegos extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, flechaR;
    Button btn_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juegos);

        final MediaPlayer correct = MediaPlayer.create(this, R.raw.correct);

        btn_1 = (ImageButton) findViewById(R.id.imageButton);
        btn_2 = (ImageButton) findViewById(R.id.imageButton2);
        btn_3 = (ImageButton) findViewById(R.id.imageButton3);
        btn_4 = (ImageButton) findViewById(R.id.imageButton4);
        btn_5 = (ImageButton) findViewById(R.id.imageButton5);
        btn_6 = (ImageButton) findViewById(R.id.imageButton6);
        flechaR = (ImageButton) findViewById(R.id.imageRight);
        btn_volver = (Button) findViewById(R.id.buttonMenu);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        flechaR.setOnClickListener(this);
        btn_volver.setOnClickListener(this);

    }




        @Override
        public void onClick(View v){
            final MediaPlayer click = MediaPlayer.create(this, R.raw.click);
        switch(v.getId()){
            case R.id.imageButton:
                click.start();
                setContentView(new Screen_1(getApplicationContext(), Juegos.this));
                break;

            case R.id.imageButton2:
                click.start();
                setContentView(new Screen_2(getApplicationContext(), Juegos.this));
                break;

            case R.id.imageButton3:
                click.start();
                setContentView(new Screen_3(getApplicationContext(), Juegos.this));
                break;
            case R.id.imageButton4:
                click.start();
                setContentView(new Screen_4(getApplicationContext(), Juegos.this));
                break;

            case R.id.imageButton5:
                click.start();
                setContentView(new Screen_5(getApplicationContext(), Juegos.this));
                break;

            case R.id.imageButton6:
                click.start();
                setContentView(new Screen_6(getApplicationContext(), Juegos.this));
                break;

            case R.id.imageRight:
                click.start();
                startActivity(new Intent(this, Juegos_2.class));
                break;

            case R.id.buttonMenu:
                click.start();
                startActivity(new Intent(this, MainActivity.class));
                break;

            default:
                break;
        }
    }
}
