package com.example.antonio.puzzle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by antonio on 7/21/16.
 */
public class Screen_2 extends View {

    private static final String TAG = "Screen_2";
    private Button botonIni;
    public Canvas c1;
    int valor = 1, valor2 = 1;
    int color;



    // Activity de la clase Play
    private Activity newActivity = null;

    /** Main bitmap */
    private Bitmap next_Bitmap = null, home_Bitmap = null, speak_Bitmap = null, save_Bitmap= null;


    private Rect Rect1, Rect2;
    private SquareArea s1, s2;
    private CircleArea x1, x2;
    private float w, h;
    int check1 = 0, check2 = 0, check3 = 0, check4 = 0;
    private boolean flag_save = false, flag_pintar = false;
    int fail = 0;
    private long tiempo1 = 0, tiempo2 = 0;
    long initialTime = 0, endTime = 0, totalTime = 0;
    VelocityTracker tracker = null;
    static float MaxVelocity_x = 0;
    static float MaxVelocity_y = 0;
    Mostrar_nivel mostrar = new Mostrar_nivel();
    Registro_datos registro = new Registro_datos();
    Level registros = new Level();
    AudioRecordTest speak = new AudioRecordTest();



    public Screen_2(Context context, Activity activity) {
        super(context);
        newActivity = activity;

        init(context);

    }

    public Screen_2(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }

    public Screen_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);

    }



    // Guardamos atributos de la clase circulo.
    public static class CircleArea {
        int radius;
        int centerX;
        int centerY;
        int num;

        CircleArea(int centerX, int centerY, int radius, int num) {
            this.radius = radius;
            this.centerX = centerX;
            this.centerY = centerY;
            this.num = num;

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




            /**
             * Paint to draw circles
             */
            private Paint yellowPaint, redPaint, bluePaint, whitePaint, greenPaint;
            private Paint red_stroke, yellow_Stroke, blue_Stroke, green_Stroke;


            //private final Random mRadiusGenerator = new Random();
            // Radius limit in pixels
            //private final static int RADIUS_LIMIT = 100;

            private static final int SHAPES_LIMIT = 2;

            /**
             * All available circles
             */
            private HashSet<CircleArea> mCircles = new HashSet<CircleArea>(SHAPES_LIMIT);
            private SparseArray<CircleArea> mCirclePointer = new SparseArray<CircleArea>(SHAPES_LIMIT);

            private HashSet<SquareArea> mSquare = new HashSet<SquareArea>(SHAPES_LIMIT);
            private SparseArray<SquareArea> mSquarePointer = new SparseArray<SquareArea>(SHAPES_LIMIT);


            /**
             * Default constructor
             *
             * @param context {@link android.content.Context}
             */


            private void init(final Context context) {
                // Generate bitmap used for background

               // sBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.success_64);
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
                // background bitmap to cover all area

               // setBackgroundResource(R.drawable.madera_1);

                color = registro.getFondo();

                switch(color){
                    case 1: setBackgroundColor(0xfffabfd0); break; //rosa

                    case 2: setBackgroundColor(0xffbce29a); break;  //verde

                    case 3: setBackgroundColor(0xffbfd3fa); break;  //azul

                    case 4: setBackgroundColor(0xffffffff); break;  //blanco

                    case 5: setBackgroundResource(R.drawable.madera_1); break;

                    default: setBackgroundResource(R.drawable.madera_1); break;
                }

                canv.drawCircle(150, 250, 100, whitePaint);
                canv.drawCircle(150, 250, 100, blue_Stroke);

                canv.drawCircle(750, 250, 100, whitePaint);
                canv.drawCircle(750, 250, 100, yellow_Stroke);

                canv.drawRect(350, 160, 550, 360, whitePaint);
                canv.drawRect(350, 160, 550, 360, red_stroke);

                canv.drawRect(950, 160, 1150, 360, whitePaint);
                canv.drawRect(950, 160, 1150, 360, green_Stroke);



                //Texto indicativo.
               /* Paint paintText = new Paint();
                paintText.setTextSize(50);
                paintText.setColor(Color.BLACK);
                paintText.setStrokeWidth(7);
                String texto = "COLOQUE LAS FICHAS";
                canv.drawText(texto, 750, 800, paintText);*/

                //imagen boton de checkeo

                if(flag_pintar== false)
                    canv.drawBitmap(save_Bitmap, 1060, 20, null);

                canv.drawBitmap(next_Bitmap, 1160, 20, null);
                canv.drawBitmap(speak_Bitmap, 120, 20, null);
                canv.drawBitmap(home_Bitmap, 20, 20, null);





                // Posición inicial de figuras dinámicas

                s1 = obtainTouchedSquare(950, 400);
                s2 = obtainTouchedSquare(900, 400);
                x1 = obtainTouchedCircle(200, 530);
                x2 = obtainTouchedCircle(300, 500);


                for (SquareArea square: mSquare) {

                    if(square.num == 3)
                        canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, redPaint);
                    else
                        canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, greenPaint);

                }


                for (CircleArea circle : mCircles) {

                    if(circle.num == 1)
                        canv.drawCircle(circle.centerX, circle.centerY, circle.radius, yellowPaint);
                    else
                        canv.drawCircle(circle.centerX, circle.centerY, circle.radius, bluePaint);

                }



            }

            @Override
            public boolean onTouchEvent(final MotionEvent event) {
                boolean handled = false;

                try {


                    CircleArea touchedCircle;
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

                            if (initialTime == 0) {
                                initialTime = System.currentTimeMillis();
                            } else {
                                endTime = System.currentTimeMillis();
                                long diff = endTime - initialTime;
                                //registro.setTime(diff);
                                if (flag_save == true)
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


                            // check if we've touched inside some Square
                            touchedSquare = obtainTouchedSquare(xTouch, yTouch);
                            touchedSquare.leftX = xTouch;
                            touchedSquare.leftY = yTouch;
                            mSquarePointer.put(event.getPointerId(0), touchedSquare);

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
                            final int pointerCount = event.getPointerCount();

                            Log.w(TAG, "Move");

                            tracker.addMovement(event); //track ACTION
                            tracker.computeCurrentVelocity(1000);
                            float x_vel = tracker.getXVelocity();
                            float y_vel = tracker.getYVelocity();

                            if (x_vel > 0.05f) {
                                MaxVelocity_x = x_vel;
                                if (flag_save == true)
                                    registros.setVelx(MaxVelocity_x); //Clase Level
                                //registro.setVelx(MaxVelocity_x);
                                Log.w(TAG, "velocidad x = " + MaxVelocity_x);
                                //registro.veloX.add(MaxVelocity_x);
                            }

                            if (y_vel > 0.05f) {
                                MaxVelocity_y = y_vel;
                                if (flag_save == true)
                                    registros.setVely(MaxVelocity_y);   //Clase Level;
                                //registro.setVely(MaxVelocity_y);
                                Log.w(TAG, "velocidad y = " + MaxVelocity_y);

                            }

                            //for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
                            // Some pointer has moved, search it by pointer id
                            pointerId = event.getPointerId(actionIndex);

                            xTouch = (int) event.getX(actionIndex);
                            yTouch = (int) event.getY(actionIndex);

                            touchedCircle = mCirclePointer.get(pointerId);
                            touchedSquare = mSquarePointer.get(pointerId);


                            if (null != touchedCircle) {
                                touchedCircle.centerX = xTouch;
                                touchedCircle.centerY = yTouch;

                            }

                            if (null != touchedSquare) {

                                touchedSquare.leftX = xTouch;
                                touchedSquare.leftY = yTouch;
                            }

                            //comprobamos que el circulo pulsado se situa en la posicion correcta.
                            if (touchedCircle.num == 1) {

                                if ((touchedCircle.centerX > 730) && (touchedCircle.centerX < 770) && (touchedCircle.centerY > 230) && (touchedCircle.centerY < 270)) {
                                    check3 = 1;
                                    touchedCircle.centerX = 750;
                                    touchedCircle.centerY = 250;
                                }

                            }

                            if (touchedCircle.num == 2) {

                                if ((touchedCircle.centerX > 130) && (touchedCircle.centerX < 170) && (touchedCircle.centerY > 230) && (touchedCircle.centerY < 270)) {
                                    check1 = 1;
                                    touchedCircle.centerX = 150;
                                    touchedCircle.centerY = 250;
                                }

                            }


                            if (touchedSquare.num == 3) {

                                if ((touchedSquare.leftX > 330) && (touchedSquare.leftX < 370) && (touchedSquare.leftY > 140) && (touchedSquare.leftY < 180)) {
                                    check2 = 1;
                                    touchedSquare.leftX = 350;
                                    touchedSquare.leftY = 160;
                                }


                            }

                            if (touchedSquare.num == 4) {

                                if ((touchedSquare.leftX > 930) && (touchedSquare.leftX < 980) && (touchedSquare.leftY > 140) && (touchedSquare.leftY < 180)) {
                                    check4 = 1;
                                    touchedSquare.leftX = 950;
                                    touchedSquare.leftY = 160;
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
                                Log.w(TAG, "PULSADO");
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


                                    Log.w(TAG, "funcionando");
                                    tiempo2 = System.currentTimeMillis();
                                    tiempo2 = tiempo2 - tiempo2;
                                    registros.setTiempo(tiempo2);
                                    mostrar.set_fallos(fail);
                                    mostrar.set_nivel(1);
                                    registros.setPuzzle(2);


                                    //registro.registrar();

                                    //newActivity.setContentView(R.layout.activity_main);
                                    Intent intent = new Intent(getContext(), Level.class);
                                    newActivity.startActivity(intent);
                                    //mostrar.set_fallos(fail);
                                    //mostrar.set_nivel(2);

                              /*  Screen_3 screen_3 = new Screen_3(getContext(), newActivity);
                                newActivity.setContentView(screen_3);*/


                                    //Intent intent = new Intent(getContext(), Level.class);
                                    //newActivity.startActivity(intent);

                                } else {
                                    fail = fail + 1;  //aumentamos la variable fail en caso de no acertar puzzle.
                                    String fallo = Integer.toString(fail);
                                    Log.w(fallo, "numero de fallos");
                                    check1 = 0;
                                    check2 = 0;
                                    check3 = 0;
                                    check4 = 0;
                                    mCircles.clear();
                                    mSquare.clear();
                                    s1 = obtainTouchedSquare(950, 400);
                                    s2 = obtainTouchedSquare(900, 400);
                                    x1 = obtainTouchedCircle(600, 400);
                                    x2 = obtainTouchedCircle(700, 500);
                                    invalidate();
                                }

                                // playActivity.setContentView();

                            } else if ((xTouch > 1) && (xTouch < 80) && (yTouch > 1) && (yTouch < 80)) {
                                Log.w(TAG, "PULSADO PAUSE");

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
                    touchedCircle = new CircleArea(xTouch, yTouch, 100, 1);
                    valor = 2;
                    break;
                case 2:
                    touchedCircle = new CircleArea(xTouch, yTouch, 100, 2);
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

            switch(valor2)
            {
                case 1:
                    touchedSquare = new SquareArea(200, 200, xTouch, yTouch, 3);
                    valor2 = 2;
                    break;
                case 2:
                    touchedSquare = new SquareArea(200, 200, xTouch, yTouch, 4);
                    valor2 = 1;
                    break;

            }


            if (mSquare.size() < 2) {
               Log.w(TAG, "Added square " + touchedSquare);
               mSquare.add(touchedSquare);
           }
        }

        return touchedSquare;
    }

    private SquareArea getTouchedSquare(final int xTouch, final int yTouch) {
        SquareArea touched = null;

        for (SquareArea square : mSquare) {
            if ((((square.leftX + 200) > xTouch) && (square.leftX < xTouch)) && (((square.leftY + 200) > yTouch) && (square.leftY < yTouch)))
            {
                Log.w(TAG, "cuadrado tocado" );
                touched = square;
                break;
            }
        }

        return touched;
    }

}




