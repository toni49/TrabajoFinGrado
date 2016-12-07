package com.example.antonio.puzzle;

/**
 * Created by antonio on 7/26/16.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashSet;
import java.util.Random;


public class Screen_3 extends View {

    private static final String TAG = "Screen_3";
    private Button botonIni;
    public Canvas canvas;
    // Activity de la clase Play
    private Activity newActivity = null;
    public Ini_screen finish;
    /**
     * Main bitmap
     */
    private Bitmap next_Bitmap = null;
    private Bitmap home_Bitmap = null;
    private Bitmap speak_Bitmap = null;
    private Bitmap save_Bitmap = null;




    private Rect Rect1, Rect2;
    private CircleArea x1, x2, x3, x4, x5;
    private float w, h;
    private long tiempo1 = 0, tiempo2 = 0;
    long endTime= 0, initialTime = 0, totalTime = 0;
    private int valor = 1;
    private boolean flag_save = false, flag_pintar = false;
    int check1 = 0, check2 = 0, check3 = 0, check4 = 0, check5 = 0;
    int fail = 0;
    VelocityTracker tracker = null;
    static float MaxVelocity_x = 0;
    static float MaxVelocity_y = 0;
    int color = 0;
    Mostrar_nivel mostrar = new Mostrar_nivel();
    MainActivity main = new MainActivity();
    Registro_datos registro = new Registro_datos();
    Level registros = new Level();


    AudioRecordTest speak = new AudioRecordTest();


    public Screen_3(Context context, Activity activity) {
        super(context);
        newActivity = activity;

        init(context);

    }

    public Screen_3(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }

    public Screen_3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);

    }


    // Guardamos atributos de la clase circulo.
    public static class CircleArea {
        int radius;
        int centerX;
        int centerY;
        int check;

        CircleArea(int centerX, int centerY, int radius, int check) {
            this.radius = radius;
            this.centerX = centerX;
            this.centerY = centerY;
            this.check = check;
        }
    }


    private Paint redPaint, bluePaint, yellowPaint, greenPaint, grayPaint;
    private Paint yellow_Stroke, blue_Stroke, red_stroke, green_Stroke, white_Stroke, gray_Stroke;
    private Paint mFondoPaint;


    private static final int CIRCLES_LIMIT = 5;

    private HashSet<CircleArea> mCircles = new HashSet<CircleArea>(CIRCLES_LIMIT);
    private SparseArray<CircleArea> mCirclePointer = new SparseArray<CircleArea>(CIRCLES_LIMIT);



    private void init(final Context context) {

        next_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.check);
        home_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.home);
        speak_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.speaker);
        save_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.register);


        greenPaint = new Paint();
        greenPaint.setColor(Color.GREEN);
        greenPaint.setStyle(Paint.Style.FILL);

        bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setStyle(Paint.Style.FILL);

        redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.FILL);

        yellowPaint = new Paint();
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setStyle(Paint.Style.FILL);

        grayPaint = new Paint();
        grayPaint.setColor(Color.GRAY);
        grayPaint.setStyle(Paint.Style.FILL);

        mFondoPaint = new Paint();
        mFondoPaint.setColor(Color.WHITE);
        mFondoPaint.setStyle(Paint.Style.FILL);

        red_stroke = new Paint();
        red_stroke.setStyle(Paint.Style.STROKE);
        red_stroke.setStrokeWidth(7);
        red_stroke.setColor(Color.RED);

        green_Stroke = new Paint();
        green_Stroke.setStyle(Paint.Style.STROKE);
        green_Stroke.setStrokeWidth(7);
        green_Stroke.setColor(Color.GREEN);

        yellow_Stroke = new Paint();
        yellow_Stroke.setStyle(Paint.Style.STROKE);
        yellow_Stroke.setStrokeWidth(7);
        yellow_Stroke.setColor(Color.YELLOW);

        blue_Stroke = new Paint();
        blue_Stroke.setStyle(Paint.Style.STROKE);
        blue_Stroke.setStrokeWidth(7);
        blue_Stroke.setColor(Color.BLUE);

        gray_Stroke = new Paint();
        gray_Stroke.setStyle(Paint.Style.STROKE);
        gray_Stroke.setStrokeWidth(7);
        gray_Stroke.setColor(Color.GRAY);

        white_Stroke = new Paint();
        white_Stroke.setStyle(Paint.Style.STROKE);
        white_Stroke.setStrokeWidth(7);
        white_Stroke.setColor(Color.WHITE);

    }

    @Override
    public void onDraw(final Canvas canv) {


        //setBackgroundResource(R.drawable.madera_1);

        color = registro.getFondo();

        switch(color){
            case 1: setBackgroundColor(0xfffabfd0); break; //rosa

            case 2: setBackgroundColor(0xffbce29a); break;  //verde

            case 3: setBackgroundColor(0xffbfd3fa); break;  //azul

            case 4: setBackgroundColor(0xffffffff); break;  //blanco

            case 5: setBackgroundResource(R.drawable.madera_1); break;

            default: setBackgroundResource(R.drawable.madera_1); break;
        }

        //Cirulos fijos
        canv.drawCircle(120, 250, 60, mFondoPaint);
        canv.drawCircle(120, 250, 60, blue_Stroke);

        canv.drawCircle(280, 250, 80, mFondoPaint);
        canv.drawCircle(280, 250, 80, red_stroke);

        canv.drawCircle(480, 250, 100, mFondoPaint);
        canv.drawCircle(480, 250, 100, yellow_Stroke);

        canv.drawCircle(720, 250, 120, mFondoPaint);
        canv.drawCircle(720, 250, 120, green_Stroke);

        canv.drawCircle(1000, 250, 135, mFondoPaint);
        canv.drawCircle(1000, 250, 135, gray_Stroke);


        //Texto indicativo.
       /* Paint paintText = new Paint();
        paintText.setTextSize(50);
        paintText.setColor(Color.BLACK);
        paintText.setStrokeWidth(5);
        String texto = "COLOQUE LAS FICHAS";
        canv.drawText(texto, 750, 900, paintText);*/

        //imagen boton de checkeo
       // w = 1740;
      //  h = 790;

        if(flag_pintar == false)
            canv.drawBitmap(save_Bitmap, 1060, 20, null);

        canv.drawBitmap(next_Bitmap, 1160, 20, null);
        canv.drawBitmap(speak_Bitmap, 120, 20, null);
        canv.drawBitmap(home_Bitmap, 20, 20, null);


        // Posición inicial de figuras dinámicas

        x1 = obtainTouchedCircle(900, 600);
        x2 = obtainTouchedCircle(750, 600);
        x3 = obtainTouchedCircle(600, 500);
        x4 = obtainTouchedCircle(150, 600);
        x5 = obtainTouchedCircle(300, 500);



        for (CircleArea circle : mCircles) {
            if(circle.radius==60)
                canv.drawCircle(circle.centerX, circle.centerY, 60, bluePaint);
            else if(circle.radius == 80)
                canv.drawCircle(circle.centerX, circle.centerY, 80, redPaint);
            else if (circle.radius == 100)
                canv.drawCircle(circle.centerX, circle.centerY, 100, yellowPaint);
            else if (circle.radius == 120)
                canv.drawCircle(circle.centerX, circle.centerY, 120, greenPaint);
            else
                canv.drawCircle(circle.centerX, circle.centerY, 135, grayPaint);



        }

    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        boolean handled = false;

        CircleArea touchedCircle;

        int xTouch;
        int yTouch;
        int pointerId;
        int actionIndex = event.getActionIndex();


        // get touch event coordinates and make transparent circle from it
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

                //Tiempo total por puzzle
                if(flag_save) {
                    if(tiempo1 == 0) {
                        tiempo1 = System.currentTimeMillis();

                    }
                    Log.w(TAG, "tiempo parcial= " + totalTime);
                }


                //tiempo entre pulsaciones del usuario.
                if(initialTime == 0){
                    initialTime = System.currentTimeMillis();
                }
                else{
                    endTime = System.currentTimeMillis();
                    long diff = endTime - initialTime;
                    //registro.setTime(diff);
                    if(flag_save)
                        registros.setTime(diff); //Clase Level

                    initialTime = endTime;
                    Log.i("Screen_1", "Time between clicks: " + diff);
                }

                ////////////////////////////////////////

                if (tracker == null) {
                    tracker = VelocityTracker.obtain();
                } else {
                    tracker.clear();
                }

                xTouch = (int) event.getX(0);
                yTouch = (int) event.getY(0);


                // check if we've touched inside some Circle
                touchedCircle = obtainTouchedCircle(xTouch, yTouch);
                touchedCircle.centerX = xTouch;
                touchedCircle.centerY = yTouch;

                mCirclePointer.put(event.getPointerId(0), touchedCircle);


                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                //handled = true;
                break;


            case MotionEvent.ACTION_MOVE:
                //final int pointerCount = event.getPointerCount();

                tracker.addMovement(event); //track ACTION
                tracker.computeCurrentVelocity(1000);
                float x_vel = tracker.getXVelocity();
                float y_vel = tracker.getYVelocity();

                if(x_vel > 0.05f){
                    MaxVelocity_x = x_vel;
                    if(flag_save)
                        registros.setVelx(MaxVelocity_x); //Clase Level
                    //registro.setVelx(MaxVelocity_x);
                    Log.w(TAG, "velocidad x = " + MaxVelocity_x);
                    //registro.veloX.add(MaxVelocity_x);
                }

                if(y_vel > 0.05f){
                    MaxVelocity_y = y_vel;
                    if(flag_save)
                        registros.setVely(MaxVelocity_y);   //Clase Level;
                    //registro.setVely(MaxVelocity_y);
                    Log.w(TAG, "velocidad y = " + MaxVelocity_y);

                }

                Log.w(TAG, "Move");

                //for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
                    // Some pointer has moved, search it by pointer id
                    pointerId = event.getPointerId(actionIndex);

                    xTouch = (int) event.getX(actionIndex);
                    yTouch = (int) event.getY(actionIndex);

                    touchedCircle = mCirclePointer.get(pointerId);



                    if (null != touchedCircle) {
                        touchedCircle.centerX = xTouch;
                        touchedCircle.centerY = yTouch;

                    }


                    if(touchedCircle.radius == 60) {
                        //comprobamos que el circulo pulsado se situa en la posicion correcta.
                        if ((touchedCircle.centerX > 100) && (touchedCircle.centerX < 140) && (touchedCircle.centerY > 230) && (touchedCircle.centerY < 270)) {
                            Log.w(TAG, "circulo 1");
                            touchedCircle.centerX = 120;
                            touchedCircle.centerY = 250;

                            //if(check1 == 0)
                            check1 = 1;

                        }
                    }

                    if(touchedCircle.radius == 80) {

                        if ((touchedCircle.centerX > 260) && (touchedCircle.centerX < 300) && (touchedCircle.centerY > 230) && (touchedCircle.centerY < 270)) {
                            Log.w(TAG, "circulo 2");
                            touchedCircle.centerX = 280;
                            touchedCircle.centerY = 250;

                            check2 = 1;

                        }
                    }

                    if(touchedCircle.radius == 100) {

                        if ((touchedCircle.centerX > 460) && (touchedCircle.centerX < 500) && (touchedCircle.centerY > 230) && (touchedCircle.centerY < 270)) {
                            Log.w(TAG, "circulo 2");
                            touchedCircle.centerX = 480;
                            touchedCircle.centerY = 250;

                            check3 = 1;
                        }

                    }

                    if(touchedCircle.radius == 120) {

                        if ((touchedCircle.centerX > 690) && (touchedCircle.centerX < 750) && (touchedCircle.centerY > 230) && (touchedCircle.centerY < 270)) {
                            Log.w(TAG, "circulo 2");
                            touchedCircle.centerX = 720;
                            touchedCircle.centerY = 250;

                            check4 = 1;
                        }

                    }

                    if(touchedCircle.radius == 150) {

                        if ((touchedCircle.centerX > 970) && (touchedCircle.centerX < 1030) && (touchedCircle.centerY > 230) && (touchedCircle.centerY < 270)) {
                            Log.w(TAG, "circulo 2");
                            touchedCircle.centerX = 1000;
                            touchedCircle.centerY = 250;

                            check5 = 1;
                        }

                    }



                //}
                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_UP:
                xTouch = (int) event.getX(0);
                yTouch = (int) event.getY(0);

                if ((xTouch > 1140) && (xTouch < 1220) && (yTouch > 1) && (yTouch < 60)) {
                    Log.w(TAG, "PULSADO NEXT");
                    String num = Integer.toString(check1);
                    String num1 = Integer.toString(check2);
                    String num2 = Integer.toString(check3);

                    Log.w(num, "valor check1");
                    Log.w(num1, "valor check2");
                    Log.w(num2, "valor check3");


                    //check = Comprobar();
                    if ((check1 == 1) && (check2 == 1) && (check3 == 1) && (check4 == 1) && (check5 == 1)) {

                        mostrar.set_fallos(fail);
                        mostrar.set_nivel(1);
                        registros.setPuzzle(3);
                        Log.w(TAG, "funcionando");

                        tiempo2 = System.currentTimeMillis();
                        tiempo2 = tiempo2 - tiempo1;
                        registros.setTiempo(tiempo2);


                       // mCircles.clear();       //Las piezas se borran y se vuelven a dibujar en la posicion exacto, creando un efecto de colocación.
                       // x1 = obtainTouchedCircle(1200, 200);
                       // x2 = obtainTouchedCircle(200, 200);
                       // x1 = obtainTouchedCircle(1200, 200);
                        Intent intent = new Intent(getContext(), Level.class);
                        newActivity.startActivity(intent);
                        
                    }
                    else
                    {
                        fail = fail + 1;  //aumentamos la variable fail en caso de no acertar puzzle.
                        String fallo = Integer.toString(fail);
                        Log.w(fallo, "numero de fallos");
                        check1 = 0;
                        check2 = 0;
                        check3 = 0;
                        check4 = 0;
                        check5 = 0;
                        mCircles.clear();
                        x1 = obtainTouchedCircle(400, 600);
                        x2 = obtainTouchedCircle(450, 600);
                        x3 = obtainTouchedCircle(600, 500);
                        x4 = obtainTouchedCircle(700, 600);
                        x5 = obtainTouchedCircle(300, 500);
                        invalidate();
                    }
                }

                else if ((xTouch > 1) && (xTouch < 80) && (yTouch > 1) && (yTouch < 80)) {
                    Log.w(TAG, "PULSADO PAUSE");

                    /*Intent intent = new Intent();
                    intent.setClass(MainActivity.getContext(), FullscreenView.Class);
                    startActivity(intent);*/

                    newActivity.setContentView(R.layout.activity_main);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    newActivity.startActivity(intent);


                    //finish.onDestroy();
                    //System.exit(0);
                }
                else if ((xTouch > 110) && (xTouch < 180) && (yTouch > 1) && (yTouch < 80)) {
                    Log.w(TAG, "Audio Record");

                    speak.startPlaying();

                }

                else if ((xTouch > 1040) && (xTouch < 1120) && (yTouch > 1) && (yTouch < 80)) {
                    Log.w(TAG, "Guardar variables");
                    flag_pintar = true;
                    flag_save = true;

                }

                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                //handled = true;
                break;


            case MotionEvent.ACTION_CANCEL:
                handled = true;
                break;

            default:
                // do nothing
                break;
        }

        return true; //super.onTouchEvent(event) || handled;
    }

    /**
     * Clears all CircleArea - pointer id relations
     */
  /*  private void clearCirclePointer() {
        Log.w(TAG, "clearCirclePointer");

        mCirclePointer.clear();
    }*/

    /**
     * Search and creates new (if needed) circle based on touch area
     *
     * @param xTouch int x of touch
     * @param yTouch int y of touch
     * @return obtained {@link CircleArea}
     */
    private CircleArea obtainTouchedCircle(final int xTouch, final int yTouch) {
        CircleArea touchedCircle = getTouchedCircle(xTouch, yTouch);


        if (null == touchedCircle) {

            switch(valor) {

                case 1:
                    touchedCircle = new CircleArea(xTouch, yTouch, 60, 0);
                    valor = 2;
                    break;

                case 2:
                    touchedCircle = new CircleArea(xTouch, yTouch, 80, 0);
                    valor = 3;
                    break;

                case 3:
                    touchedCircle = new CircleArea(xTouch, yTouch, 100, 0);
                    valor = 4;
                    break;

                case 4:
                    touchedCircle = new CircleArea(xTouch, yTouch, 120, 0);
                    valor = 5;
                    break;

                case 5:
                    touchedCircle = new CircleArea(xTouch, yTouch, 150, 0);
                    valor = 1;
                    break;


            }

            if (mCircles.size() < 5) {
                Log.w(TAG, "Added circle " + touchedCircle);
                mCircles.add(touchedCircle);
            }
        }

        return touchedCircle;
    }

    /**
     * Determines touched circle
     *
     * @param xTouch int x touch coordinate
     * @param yTouch int y touch coordinate
     * @return {@link CircleArea} touched circle or null if no circle has been touched
     */
    private CircleArea getTouchedCircle(final int xTouch, final int yTouch) {
        CircleArea touched = null;

        for (CircleArea circle : mCircles) {
            if ((circle.centerX - xTouch) * (circle.centerX - xTouch) + (circle.centerY - yTouch) * (circle.centerY - yTouch) <= circle.radius * circle.radius) {
                touched = circle;
                break;
            }
        }

        return touched;
    }
    //////////////////////////////////////////////////////////////////////////////////////

}


