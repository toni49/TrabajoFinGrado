package com.example.antonio.puzzle;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by antonio on 11/5/16.
 */
public class Registro_datos extends AppCompatActivity {

    private static final String TAG = "set velx";


    private static float MaxVeloX = 0, MaxVeloY = 0;
    private static int colorFondo = 0;
    private static String username = "";

    //private static float time = 0;

    public static List<Float> veloX = new ArrayList<Float>();
    public static List<Float> veloY = new ArrayList<Float>();
    public static List<Float> time = new ArrayList<Float>();

    public String getDate(){
        Calendar date = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(date.getTime());
        return formattedDate;
    }


    public void setTime(float t){
        time.add(t);
    }

    public List<Float> getTime(){
        return time;
    }


    //Registramos el color de fondo;

    public void setFondo(int var) { colorFondo = var;}

    public int getFondo() { return colorFondo;}


    //Registramos los valores de las velocidades maximas.

    public void setVeloX(float x){
        MaxVeloX = x;
    }

    public void setVeloY(float y){
        MaxVeloY = y;
    }

    public float getVeloX(){
        return MaxVeloX;
    }

    public float getVeloY(){
        return MaxVeloY;
    }

    ////////////////////////////////////////////////////77

    public void setUsername(String name){
        username = name;
    }

    public String getUsername(){
        return username;
    }

    public void setVelx(float x){
        veloX.add(x);
    }

    public List<Float> getVelx(){
        return veloX;
    }

    public void setVely(float y){
        veloY.add(y);
    }

    public List<Float> getVely(){
        return veloY;
    }

    public float calcMediaVelX(){
        float media = 0;
        for(int i=0; i< getVelx().size(); i++)
            media += getVelx().get(i);

        media = media / getVelx().size();
        Log.w(TAG, "Media velocidad x = " + media);
        return media;
    }

    public float calcMediaVelY(){
        float media = 0;
        for(int i=0; i< getVely().size(); i++)
            media += getVely().get(i);

        media = media / getVely().size();
        Log.w(TAG, "Media velocidad y = " + media);
        return media;
    }

    public float mediaTiempos(){
        float media = 0;

        for(int i=0; i<getTime().size(); i++)
            media += getTime().get(i);

        Log.w(TAG, "Media tiempos = " + media);
        media = media / getTime().size();
        return media;
    }




    Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");
                if (success) {
                    //click.start();// Ir a LoginActivity

                }
                else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.nivel);



        float maxVeloX, maxVeloY, timeMedio;
        Mostrar_nivel fallos = new Mostrar_nivel();
        int var = fallos.get_fallos();

        maxVeloX = calcMediaVelX();
        maxVeloY = calcMediaVelY();
        timeMedio = mediaTiempos();

        //calcular la media de la velocidad y , de la velocidad x y de las diferencias de tiempo;

        //for(int i=0; i<veloX.size(); i++) {

            RegisterData registerData = new RegisterData(getUsername(), getDate(), 1, maxVeloY, maxVeloX, var, timeMedio,  responseListener);
            RequestQueue queue = Volley.newRequestQueue(Registro_datos.this);
            queue.add(registerData);
        //}

            //float MaxVeloX = this.getVelx(i);
            //float MaxVeloY = veloY.get(i);
            //Log.w(TAG, "velocidad x = " + veloX);



    }

}
