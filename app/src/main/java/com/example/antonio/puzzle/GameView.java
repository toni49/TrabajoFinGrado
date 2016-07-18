package com.example.antonio.puzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.SurfaceView;

/**
 * Created by antonio on 7/18/16.
 */
public class GameView extends SurfaceView implements Runnable {

    private Bitmap gameImage;
    private Thread gameThread;
    public boolean running = false;
    //private Estado nivel;
    private Estado currentState;
    private Inputs inputHandler;

    public GameView(Context context, int width, int height)
    {
        super(context);
        gameImage= Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
    }

    public GameView(Context context)
    {
        super(context);
    }

    public void initGame()
    {
        running = true;
        gameThread = new Thread (this, "Game Thread");
        gameThread.start();

    }

    public void pauseGame()
    {
        running = false;
        while(gameThread.isAlive())
        {
            try {
                    gameThread.join();
                    break;
                }catch(InterruptedException e){

            }
        }
    }

    public void setCurrentState(Estado newEstado)
    {
        System.gc();
        newEstado.init();
        currentState = newEstado;
    }

    public void run()
    {
        //mostrar imaganes dependiendo del nivel y
        // esperar a que las piezas sean encajadas correctamente.

        while(running)
        {
            //update();
        }

    }

    private void initInput()
    {
        if(inputHandler == null)
        {
            inputHandler = new Inputs();
        }
        setOnTouchListener(inputHandler);
    }


}


