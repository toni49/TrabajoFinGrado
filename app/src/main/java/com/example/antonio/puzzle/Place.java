package com.example.antonio.puzzle;

import android.media.Image;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * Created by antonio on 7/17/16.
 */

public class Place {

    private int x;

    private int y;

    private Piezas piezas;

    private Board board;




    public Place(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public int getY(){
        return y;
    }

    public int getX(){
        return x;
    }




}
