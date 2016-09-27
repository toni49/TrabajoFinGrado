package com.example.antonio.puzzle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.TextView;

/**
 * Created by antonio on 9/26/16.
 */
public class pruebas_vel extends Activity {


        TextView textAvtion, textVelocityX, textVelocityY,
                textMaxVelocityX, textMaxVelocityY, textTime;

        VelocityTracker velocityTracker = null;

        float maxXVelocity;
        float maxYVelocity;
        long initialTime = 0;
        long endTime = 0;
        long diff = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.velocidades);
            textAvtion = (TextView) findViewById(R.id.action);
            textVelocityX = (TextView) findViewById(R.id.velocityx);
            textVelocityY = (TextView) findViewById(R.id.velocityy);
            textMaxVelocityX = (TextView) findViewById(R.id.maxvelocityx);
            textMaxVelocityY = (TextView) findViewById(R.id.maxvelocityy);
            textTime = (TextView) findViewById(R.id.time);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            int action = event.getActionMasked();

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if (velocityTracker == null) {
                        velocityTracker = VelocityTracker.obtain();
                    } else {
                        velocityTracker.clear();
                    }
                    velocityTracker.addMovement(event);
                    maxXVelocity = 0;
                    maxYVelocity = 0;



                    if(initialTime == 0){
                        initialTime = System.currentTimeMillis();
                    }
                    else{
                        endTime = System.currentTimeMillis();
                        diff = endTime - initialTime;
                        initialTime = endTime;
                        Log.i("Screen_5", "Time between clicks: " + diff);
                    }

                    textVelocityX.setText("X-velocity (pixel/s): 0");
                    textVelocityY.setText("Y-velocity (pixel/s): 0");
                    textMaxVelocityX.setText("max. X-velocity: 0");
                    textMaxVelocityY.setText("max. Y-velocity: 0");
                    textTime.setText("time between touches: 0");


                    break;
                case MotionEvent.ACTION_MOVE:
                    velocityTracker.addMovement(event);
                    velocityTracker.computeCurrentVelocity(1000);
                    //1000 provides pixels per second

                    float xVelocity = velocityTracker.getXVelocity();
                    float yVelocity = velocityTracker.getYVelocity();

                    if(xVelocity > maxXVelocity){
                        //max in right side
                        maxXVelocity = xVelocity;
                    }

                    if(yVelocity > maxYVelocity){
                        //Max in down side
                        maxYVelocity = yVelocity;
                    }

                    textVelocityX.setText("X-velocity (pixel/s): " + xVelocity);
                    textVelocityY.setText("Y-velocity (pixel/s): " + yVelocity);
                    textMaxVelocityX.setText("max. X-velocity: " + maxXVelocity);
                    textMaxVelocityY.setText("max. Y-velocity: " + maxYVelocity);
                    textTime.setText("time between touches (milliseconds): " + diff);


                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                   // velocityTracker.recycle();
                    break;
            }

            return true;
        }

}
