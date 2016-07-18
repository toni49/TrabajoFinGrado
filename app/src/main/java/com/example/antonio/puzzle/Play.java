package com.example.antonio.puzzle;

import android.os.Bundle;
import android.support.annotation.Size;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by antonio on 7/16/16.
 */
public class Play extends AppCompatActivity implements View.OnTouchListener {

    public static final int GAME_HEIGHT = 1000;
    public static final int GAME_WIDTH = 1000;
    private ArrayList<Piezas> piezas;
    public static GameView sGame;
    ImageView ima;
    boolean moving = false;
    float x, y = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        // assets = getAssets();
        ima = (ImageView)findViewById(R.id.imageView3);
        ima.setOnTouchListener((OnTouchListener) this);
        //sGame = new GameView (this, GAME_WIDTH, GAME_HEIGHT);
        //setContentView(sGame);


    }

    public boolean onTouch(View v, MotionEvent event)
    {
        int action = MotionEventCompat.getActionMasked(event);
        int corx = (int) event.getX();
        int cory = (int) event.getY();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                moving = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(moving){
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
    }




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



}
