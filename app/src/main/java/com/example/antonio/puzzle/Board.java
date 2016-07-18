package com.example.antonio.puzzle;

import android.content.Context;
import android.location.Location;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by antonio on 7/17/16.
 */
public class Board extends View {

    private float width;
    private float height;

    private Board board;

    //Inicializa un nuevo fondo de pantalla

    public Board(Context context, Board board)
    {
        super(context); //llamada al constructor de la clase padre
        this.board = board;
        setFocusable(true);
        setFocusableInTouchMode(true);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() != MotionEvent.ACTION_DOWN)
            return super.onTouchEvent(event);
        //Place p = location(event.getX(), event.getY());

        return true;
    }
}
