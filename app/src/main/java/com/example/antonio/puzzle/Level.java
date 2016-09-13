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
    ImageView imagen, imagen2, imagen3, imagen4, flash_stars;
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
        imagen2 = (ImageView) findViewById(R.id.imageStar1);
        imagen3 = (ImageView) findViewById(R.id.imageStar2);
        imagen4 = (ImageView)findViewById(R.id.imageStar3);
        flash_stars = (ImageView)findViewById(R.id.imageStars);


        next_level.setOnClickListener(this);
        salir.setOnClickListener(this);

        int y = mostrar.get_nivel();
        int fail = mostrar.get_fallos();

        String err = Integer.toString(fail);



        switch(y){
            case 1:
                correct.start();
                txt.setText("Nivel 1");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                flash_stars.setImageResource(R.drawable.stars);
                zoom(imagen2);
                move(flash_stars);
                //imagen.setImageResource(R.drawable.);
                valor = 1;
                break;
            case 2:
                correct.start();
                txt.setText("Nivel 2");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                imagen3.setImageResource(R.drawable.yellow_star);
                zoom(imagen3);
                //slide(imagen);
                valor = 2;
                break;
            case 3:
                txt.setText("Nivel 3");
                txtfallos.setText("Número de fallos: " + err);
                valor = 3;
                break;
            default:
                txt.setText("Nivel --");
                break;

        }


    }

    public void zoom(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        view.startAnimation(animation);
    }

    public void move(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        view.startAnimation(animation);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_next:
                if(valor == 1)
                    setContentView(new Screen_6(getApplicationContext(), Level.this));
                else if (valor == 2)
                    setContentView(new Screen_ima(getApplicationContext(), Level.this));
                else if (valor == 3)
                    setContentView(new Screen_ima(getApplicationContext(), Level.this));
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
