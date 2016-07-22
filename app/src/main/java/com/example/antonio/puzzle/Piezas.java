package com.example.antonio.puzzle;

import android.graphics.Rect;

/**
 * Created by antonio on 7/17/16.
 */
public class Piezas {

    private int height, width;
    private float x, y;
    private Rect rect;

    public Piezas(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        rect = new Rect((int) x, (int) y, (int) x + width, (int) y + height);
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

}
