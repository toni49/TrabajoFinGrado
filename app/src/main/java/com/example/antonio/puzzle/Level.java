package com.example.antonio.puzzle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by antonio on 8/7/16.
 */
public class Level extends AppCompatActivity implements View.OnClickListener {

    String TAG = "class level";
    TextView txt, txtfallos;
    ImageView imagen;
    Button next_level, salir;
    int valor = 0;


    Mostrar_nivel mostrar = new Mostrar_nivel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nivel);

        final MediaPlayer correct = MediaPlayer.create(this, R.raw.correct);

        next_level = (Button) findViewById(R.id.button_next);
        salir = (Button) findViewById(R.id.button_exit);
        txt = (TextView) findViewById(R.id.texto1);
        txtfallos = (TextView) findViewById(R.id.text_fallos);
        imagen = (ImageView) findViewById(R.id.imageView4);

        next_level.setOnClickListener(this);
        salir.setOnClickListener(this);

        int y = mostrar.get_nivel();
        int fail = mostrar.get_fallos();

        String err = Integer.toString(fail);



        switch(y){
            case 1:
                txt.setText("Nivel 1");
                txtfallos.setText("Número de fallos: " + err);
                //imagen.setImageResource(R.drawable.);
                valor = 1;
                break;
            case 2:
                correct.start();
                txt.setText("Nivel 2");
                txtfallos.setText("Número de fallos: " + err);
                imagen.setImageResource(R.drawable.ping_2);
                slide(imagen);
                valor = 2;
                break;
            case 3:
                txt.setText("Nivel 3");
                txtfallos.setText("Número de fallos: " + err);
                valor = 3;
                break;
            case 4:
                txt.setText("Nivel 4");
                txtfallos.setText("Número de fallos: " + err);
                valor = 4;
                break;
            case 5:
                txt.setText("Nivel 5");
                txtfallos.setText("Número de fallos: " + err);
                valor = 5;
                break;
            default:
                txt.setText("Nivel --");
                break;

        }


    }

    public void slide(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        imagen.startAnimation(animation);
        //imagen.setImageResource(R.drawable.ping_1);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_next:
                if(valor == 1)
                    setContentView(new Screen_3(getApplicationContext(), Level.this));
                else if (valor == 2)
                    setContentView(new Screen_4(getApplicationContext(), Level.this));
                else if (valor == 3)
                    setContentView(new Screen_ima(getApplicationContext(), Level.this));
                else if (valor == 4)
                    setContentView(new Screen_ima3(getApplicationContext(), Level.this));
                break;

            case R.id.button_exit:
                setContentView(R.layout.activity_main);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }




}
