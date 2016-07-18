package com.example.antonio.puzzle;

import android.view.MotionEvent;

/**
 * Created by antonio on 7/18/16.
 */
public abstract class Estado {

    private int nivel;

    public void setCurrentState(Estado newEstado)
    {
        Play.sGame.setCurrentState(newEstado);
    }

    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);

    public abstract void init();

    public abstract void render(Painter g);

    public Estado(int nivel)
    {
        this.nivel = Getnivel();
    }

    private int Getnivel() {
        return nivel;
    }
}
