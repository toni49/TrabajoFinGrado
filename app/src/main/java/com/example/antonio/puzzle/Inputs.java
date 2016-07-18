package com.example.antonio.puzzle;

import android.view.MotionEvent;
import android.view.View;


/**
 * Created by antonio on 7/18/16.
 */
public class Inputs implements View.OnTouchListener {

    private Estado currentState;

    public void setCurrentState(Estado currentState)
    {
        this.currentState = currentState;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int scaledX = (int) (motionEvent.getX() / view.getWidth() * Play.GAME_WIDTH);
        int scaledY = (int) (motionEvent.getY() / view.getHeight() * Play.GAME_HEIGHT);

        return currentState.onTouch(motionEvent, scaledX, scaledY);
    }
}
