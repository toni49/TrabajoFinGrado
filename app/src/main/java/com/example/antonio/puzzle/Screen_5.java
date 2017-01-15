package com.example.antonio.puzzle;

/**
 * Created by antonio on 8/5/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;




public class Screen_5 extends View {

    private static final String TAG = "Screen_5";
    private Button botonIni;
    public Canvas canvas;
    // Activity de la clase Play
    private Activity newActivity = null;



    Mostrar_nivel mostrar = new Mostrar_nivel();
    Registro_datos registro = new Registro_datos();
    AudioRecordTest speak = new AudioRecordTest();
    Level registros = new Level();

    private Bitmap next_Bitmap = null;
    private Bitmap home_Bitmap = null;
    private Bitmap speak_Bitmap = null;
    private Bitmap save_Bitmap = null;
    private SquareArea x1, x2, x3, x4;
    private int valor = 1;

    private boolean flag_save = false, flag_pintar = false;
    int check1 = 0, check2 = 0, check3 = 0, check4 = 0;
    int fail = 0;
    int color;
    long initialTime = 0, endTime = 0, totalTime = 0;
    private long tiempo1 = 0, tiempo2 = 0;
    VelocityTracker tracker = null;
    static float MaxVelocity_x = 0;
    static float MaxVelocity_y = 0;
    MainActivity main = new MainActivity();


    public Screen_5(Context context, Activity activity) {
        super(context);
        newActivity = activity;

        init(context);

    }

    public Screen_5(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }

    public Screen_5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }


    // Guardamos atributos de la clase circulo.
    public static class CircleArea {
        int radius;
        int centerX;
        int centerY;

        CircleArea(int centerX, int centerY, int radius) {
            this.radius = radius;
            this.centerX = centerX;
            this.centerY = centerY;
        }
    }


    // Guardamos atributos de la clase cuadrado.
    public static class SquareArea {
        int Swidth;
        int Sheight;
        int leftY;
        int leftX;
        int num;

        SquareArea(int Swidth, int Sheight, int leftX, int leftY, int num) {
            this.Swidth = Swidth;
            this.Sheight = Sheight;
            this.leftX = leftX;
            this.leftY = leftY;
            this.num = num;

        }
    }


    private Paint whitePaint;
    private Paint bluePaint;
    private Paint greenPaint;
    private Paint red_stroke, green_Stroke, blue_Stroke, yellow_Stroke;
    private Paint redPaint;
    private Paint yellowPaint;


    private static final int CIRCLES_LIMIT = 6;

    private HashSet<CircleArea> mCircles = new HashSet<CircleArea>(CIRCLES_LIMIT);
    private SparseArray<CircleArea> mCirclePointer = new SparseArray<CircleArea>(CIRCLES_LIMIT);

    private HashSet<SquareArea> mSquare = new HashSet<SquareArea>(CIRCLES_LIMIT);
    private SparseArray<SquareArea> mSquarePointer = new SparseArray<SquareArea>(CIRCLES_LIMIT);



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

        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStyle(Paint.Style.FILL);

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


        canv.drawRect(100, 150, 500, 200, whitePaint);
        canv.drawRect(100, 150, 500, 200, red_stroke);

        canv.drawRect(70, 240, 120, 600, whitePaint);
        canv.drawRect(70, 240, 120, 600, green_Stroke);

        canv.drawRect(100, 630, 500, 680, whitePaint);
        canv.drawRect(100, 630, 500, 680, blue_Stroke);

        canv.drawRect(480, 240, 530, 600, whitePaint);
        canv.drawRect(480, 240, 530, 600, yellow_Stroke);



        //Texto indicativo.
       /* Paint paintText = new Paint();
        paintText.setTextSize(40);
        paintText.setColor(Color.BLACK);
        paintText.setStrokeWidth(7);
        String texto = "COLOQUE LAS FICHAS";
        canv.drawText(texto, 750, 800, paintText);*/



        //imagen boton de checkeo
        if(flag_pintar == false)
        canv.drawBitmap(save_Bitmap, 1060, 20, null);

        canv.drawBitmap(next_Bitmap, 1160, 20, null);
        canv.drawBitmap(speak_Bitmap, 120, 20, null);
        canv.drawBitmap(home_Bitmap, 20, 20, null);



        // Posición inicial de figuras dinámicas

        x1 = obtainTouchedSquare(700, 200);
        x2 = obtainTouchedSquare(700, 500);
        x3 = obtainTouchedSquare(900, 300);
        x4 = obtainTouchedSquare(800, 300);


        for (SquareArea square : mSquare) {
            if(square.num == 1)
                canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, redPaint);
            else if(square.num == 2)
                canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, bluePaint);
            else if(square.num == 3)
                canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, greenPaint);
            else
                canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, yellowPaint);
        }




    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        boolean handled = false;

        try {

            //CircleArea touchedCircle;
            SquareArea touchedSquare;

            int xTouch;
            int yTouch;
            int pointerId;
            int actionIndex = event.getActionIndex();


            // get touch event coordinates and make transparent circle from it
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:

                    //Tiempo total por puzzle
                    if (flag_save) {
                        if (tiempo1 == 0) {
                            tiempo1 = System.currentTimeMillis();

                        }
                        Log.w(TAG, "tiempo parcial= " + totalTime);
                    }


                    //tiempo entre pulsaciones del usuario.
                    if (initialTime == 0) {
                        initialTime = System.currentTimeMillis();
                    } else {
                        endTime = System.currentTimeMillis();
                        long diff = endTime - initialTime;
                        //registro.setTime(diff);
                        if (flag_save)
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

                    tracker.addMovement(event); //track ACTION
                    MaxVelocity_y = 0;
                    MaxVelocity_x = 0;

                    xTouch = (int) event.getX(0);
                    yTouch = (int) event.getY(0);

                    // check if we've touched inside some Circle
                    touchedSquare = obtainTouchedSquare(xTouch, yTouch);
                    touchedSquare.leftX = xTouch;
                    touchedSquare.leftY = yTouch;

                    mSquarePointer.put(event.getPointerId(0), touchedSquare);


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

                    if (x_vel > 0.05f) {
                        MaxVelocity_x = x_vel;
                        if (flag_save)
                            registros.setVelx(MaxVelocity_x); //Clase Level
                        //registro.setVelx(MaxVelocity_x);
                        Log.w(TAG, "velocidad x = " + MaxVelocity_x);
                        //registro.veloX.add(MaxVelocity_x);
                    }

                    if (y_vel > 0.05f) {
                        MaxVelocity_y = y_vel;
                        if (flag_save)
                            registros.setVely(MaxVelocity_y);   //Clase Level;
                        //registro.setVely(MaxVelocity_y);
                        Log.w(TAG, "velocidad y = " + MaxVelocity_y);

                    }


                    pointerId = event.getPointerId(actionIndex);

                    xTouch = (int) event.getX(actionIndex);
                    yTouch = (int) event.getY(actionIndex);

                    touchedSquare = mSquarePointer.get(pointerId);


                    if (null != touchedSquare) {
                        touchedSquare.leftX = xTouch;
                        touchedSquare.leftY = yTouch;

                    }

                    if (touchedSquare.num == 1) {
                        if ((touchedSquare.leftX > 80) && (touchedSquare.leftX < 120) && (touchedSquare.leftY > 130) && (touchedSquare.leftY < 170)) {
                            Log.w(TAG, "fig 1");
                            check1 = 1;
                            touchedSquare.leftX = 100;
                            touchedSquare.leftY = 150;
                        }
                    }

                    if (touchedSquare.num == 2) {
                        if ((touchedSquare.leftX > 80) && (touchedSquare.leftX < 120) && (touchedSquare.leftY > 610) && (touchedSquare.leftY < 650)) {
                            Log.w(TAG, "fig 2");
                            check2 = 1;
                            touchedSquare.leftX = 100;
                            touchedSquare.leftY = 630;

                        }
                    }

                    if (touchedSquare.num == 3) {
                        if ((touchedSquare.leftX > 50) && (touchedSquare.leftX < 90) && (touchedSquare.leftY > 220) && (touchedSquare.leftY < 260)) {
                            check3 = 1;
                            touchedSquare.leftX = 70;
                            touchedSquare.leftY = 240;
                            Log.w(TAG, "fig 3");
                        }
                    }

                    if (touchedSquare.num == 4) {
                        if ((touchedSquare.leftX > 460) && (touchedSquare.leftX < 500) && (touchedSquare.leftY > 220) && (touchedSquare.leftY < 260)) {
                            check4 = 1;
                            touchedSquare.leftX = 480;
                            touchedSquare.leftY = 240;
                            Log.w(TAG, "fig 4");
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
                        String num3 = Integer.toString(check4);
                        Log.w(num, "valor check1");
                        Log.w(num1, "valor check2");
                        Log.w(num2, "valor check3");
                        Log.w(num3, "valor check4");

                        //check = Comprobar();
                        if ((check1 == 1) && (check2 == 1) && (check3 == 1) && (check4 == 1)) {


                            tiempo2 = System.currentTimeMillis();
                            tiempo2 = tiempo2 - tiempo1;
                            registros.setTiempo(tiempo2);

                            mostrar.set_fallos(fail);
                            mostrar.set_nivel(2);
                            registros.setPuzzle(5);
                            Log.w(TAG, "funcionando");

                            // newActivity.setContentView(R.layout.prueba);
                            // Intent intent = new Intent(getContext(), Level.class);
                            // newActivity.startActivity(intent);

                            Intent intent = new Intent(getContext(), Level.class);
                            newActivity.startActivity(intent);

                            // Screen_4 screen_4 = new Screen_4(getContext(), newActivity);
                            // newActivity.setContentView(screen_4);


                            // Show_level show_level = new Show_level(getContext(), newActivity);
                            // newActivity.setContentView(show_level);


                            //animation.start();
                        } else {
                            fail = fail + 1;  //aumentamos la variable fail en caso de no acertar puzzle.
                            String fallo = Integer.toString(fail);
                            Log.w(fallo, "numero de fallos");
                            check1 = 0;
                            check2 = 0;
                            check3 = 0;
                            check4 = 0;
                            mSquare.clear();
                            x1 = obtainTouchedSquare(700, 200);
                            x2 = obtainTouchedSquare(700, 500);
                            x3 = obtainTouchedSquare(900, 300);
                            x4 = obtainTouchedSquare(800, 300);
                            invalidate();
                        }
                    } else if ((xTouch > 1) && (xTouch < 80) && (yTouch > 1) && (yTouch < 80)) {
                        Log.w(TAG, "PULSADO PAUSE");

                    /*Intent intent = new Intent();
                    intent.setClass(MainActivity.getContext(), FullscreenView.Class);
                    startActivity(intent);*/

                        newActivity.setContentView(R.layout.activity_main);
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        newActivity.startActivity(intent);


                        //finish.onDestroy();
                        //System.exit(0);
                    } else if ((xTouch > 110) && (xTouch < 180) && (yTouch > 1) && (yTouch < 80)) {
                        Log.w(TAG, "Audio Record");

                        speak.startPlaying();

                    } else if ((xTouch > 1040) && (xTouch < 1120) && (yTouch > 1) && (yTouch < 80)) {
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
        }catch(NullPointerException e){e.printStackTrace();}

        return true; //super.onTouchEvent(event) || handled;
    }


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
                    touchedCircle = new CircleArea(xTouch, yTouch, 120);
                    valor = 2;
                    break;

                case 2:
                    touchedCircle = new CircleArea(xTouch, yTouch, 180);
                    valor = 1;
                    break;


            }

            if (mCircles.size() < 2) {
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

    private SquareArea obtainTouchedSquare(final int xTouch, final int yTouch) {
        SquareArea touchedSquare = getTouchedSquare(xTouch, yTouch);

        if (null == touchedSquare) {

            switch(valor) {

                case 1:
                    touchedSquare = new SquareArea(400, 50, xTouch, yTouch, 1);
                    valor = 2;
                    break;

                case 2:
                    touchedSquare= new SquareArea(400, 50, xTouch, yTouch, 2);
                    valor = 3;
                    break;

                case 3:
                    touchedSquare= new SquareArea(50, 360, xTouch, yTouch, 3);
                    valor = 4;
                    break;

                case 4:
                    touchedSquare= new SquareArea(50, 360, xTouch, yTouch, 4);
                    valor = 1;
                    break;


            }


            if (mSquare.size() < 4) {
                Log.w(TAG, "Added square " + touchedSquare);
                mSquare.add(touchedSquare);
            }
        }

        return touchedSquare;
    }

    private SquareArea getTouchedSquare(final int xTouch, final int yTouch) {
        SquareArea touched = null;

        for (SquareArea square : mSquare) {
            if ((((square.leftX + square.Swidth) > xTouch) && ((square.leftX) < xTouch)) && (((square.leftY + square.Sheight) > yTouch) && ((square.leftY) < yTouch)))
            {
                Log.w(TAG, "cuadrado tocado" );
                touched = square;
                break;
            }
        }

        return touched;
    }



}
