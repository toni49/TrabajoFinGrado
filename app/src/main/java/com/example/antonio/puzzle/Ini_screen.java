package com.example.antonio.puzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by antonio on 7/16/16.
 */
public class Ini_screen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ini_screen);

        Thread myThread = new Thread() {
            @Override
            public void run() {

                try {
                    sleep(3000); // Slpash Screen durante 3 segundos
                    Intent intent = new Intent(getApplicationContext(), Play.class);

                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();


                }
            }
        }; myThread.start();
    }
}

