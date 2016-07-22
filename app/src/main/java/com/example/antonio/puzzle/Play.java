package com.example.antonio.puzzle;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Size;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by antonio on 7/16/16.
 */
public class Play extends AppCompatActivity  {

    public static final int GAME_HEIGHT =100;
    public static final int GAME_WIDTH = 100;
    //private ArrayList<Piezas> piezas;
    public static GameView sGame;
    public CirclesDrawingView.CircleArea circle;
    ImageView ima;
    Canvas c1;
    boolean moving = false;
    float x, y = 0;

    private static final String TAG = "Play class";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log.w(TAG, "play_layout");
        setContentView(R.layout.play);
        //run();
        // assets = getAssets();


        setContentView(new CirclesDrawingView(getApplicationContext(), Play.this));// Dibujamos todas las imagenes del nivel con este ContentView.



        //ima = (ImageView)findViewById(R.id.imageView3);
        //ima.setOnTouchListener((OnTouchListener) this);
        //setContentView(new CirclesDrawingView(this));// <------------------------------


        //sGame = new GameView (this, GAME_WIDTH, GAME_HEIGHT);
        //setContentView(sGame);
    }



   /*public boolean onTouch(View v, MotionEvent event)
    {
        int action = MotionEventCompat.getActionMasked(event);
        int corx = (int) event.getX();
        int cory = (int) event.getY();
        boolean handled = false;

        switch(action){
            case MotionEvent.ACTION_DOWN:



                // check if we've touched inside some circle



                moving = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(moving)
                {
                    x= corx-ima.getWidth()/2;
                    y= cory-ima.getHeight()*3/2;
                    ima.setX(x);
                    ima.setY(y);
                }
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                break;

        }
        return true;
    }*/



/*
    public void init() {
        piezas = new ArrayList<Piezas>();
        Piezas b = new Piezas(100, 100, 60, 30);
        piezas.add(b);
    }


    public void render(Painter g)
    {
        renderPiezas(g);
    }

    private void renderPiezas(Painter g)
    {
        g.drawImage(Assets.rect_s, 100, 100);
    }

*/

}
