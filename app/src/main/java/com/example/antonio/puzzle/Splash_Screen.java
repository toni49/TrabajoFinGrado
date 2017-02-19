package com.example.antonio.puzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by antonio on 7/16/16.
 */
public class Splash_Screen extends AppCompatActivity {

    private ProgressBar barra_progreso;
    private boolean activo = false;
    private String TAG = "Ini_Screen";

    protected static final int tiempoMax = 2000;    //tiempo que duracion de carga.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);        //Iniciamos layout splash_screen

        barra_progreso = (ProgressBar) findViewById(R.id.progressBar);

        final Thread thread = new Thread() {

            public void run() {
                activo = true;
                try{
                    int waited = 0;
                    while(activo && (waited < tiempoMax))
                    {
                        sleep(200);
                        if(activo)
                        {
                            waited += 200;
                            updateProgress(waited);
                        }
                    }
                }catch(InterruptedException e){
                    //captura excepcion.
                }
                finally{
                    onPause();
                }

            }
        };thread.start();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        setContentView(R.layout.splash_screen);        //Iniciamos layout splash_screen

        barra_progreso = (ProgressBar) findViewById(R.id.progressBar);

        final Thread thread = new Thread() {

            public void run() {
                activo = true;
                try{
                    int waited = 0;
                    while(activo && (waited < tiempoMax))
                    {
                        sleep(200);
                        if(activo)
                        {
                            waited += 200;
                            updateProgress(waited);
                        }
                    }
                }catch(InterruptedException e){
                    //captura exception.
                }
                finally{
                    onPause();
                }

            }
        };thread.start();

    }

    public void updateProgress(final int tiempo)
    {
        if(null != barra_progreso){
            final int progreso = barra_progreso.getMax() * tiempo / tiempoMax;
            barra_progreso.setProgress(progreso);
        }
    }

    public void onPause()
    {
        super.onPause();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        Log.d(TAG, "La barra de progreso ha finalizado");
        startActivity(intent);
    }

    public void onDestroy()
    {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
        finish();
        System.exit(1);
    }

}

