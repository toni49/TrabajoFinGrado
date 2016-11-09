package com.example.antonio.puzzle;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio on 11/5/16.
 */
public class Registro_datos extends AppCompatActivity {

    private static float MaxVeloX = 0, MaxVeloY = 0;
    private static int colorFondo = 0;
    private static List<Float> veloX = new ArrayList<Float>();
    private static List<Float> veloY = new ArrayList<Float>();


    //Registramos el color de fondo;

    public void setFondo(int var) { colorFondo = var;}

    public int getFondo() { return colorFondo;}

    //Registramos los valores de las velocidades maximas.

    public void setVeloX(float x){ MaxVeloX = x; }

    public void setVeloY(float y){
        MaxVeloY = y;
    }

    public float getVeloX(){ return MaxVeloX; }

    public float getVeloY(){
        return MaxVeloY;
    }

    public void setVelx(float x){
        veloX.add(x);
    }

    public void setVely(float y){
        veloX.add(y);
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

        //float maxVeloX, maxVeloY;

        //maxVeloX = getVeloX();
        //maxVeloY = getVeloY();

        for(int i=0; i <veloX.size(); i++) {

            RegisterData registerData = new RegisterData(veloX, veloY, responseListener);
            RequestQueue queue = Volley.newRequestQueue(Registro_datos.this);
            queue.add(registerData);
        }


    }
}
