package com.example.antonio.puzzle;

/**
 * Created by antonio on 7/29/16.
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
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;




public class Screen_4 extends View {

    private static final String TAG = "Screen_4";
    private Button botonIni;
    public Canvas canvas;
    // Activity de la clase Play
    private Activity newActivity = null;
    Mostrar_nivel mostrar = new Mostrar_nivel();
    Registro_datos registro = new Registro_datos();

    AudioRecordTest speak = new AudioRecordTest();
    Level registros = new Level();


    /**
     * Main bitmap
     */
    private Bitmap next_Bitmap = null;
    private Bitmap home_Bitmap = null;
    private Bitmap speak_Bitmap = null;
    private Bitmap save_Bitmap = null;


    private Rect Rect1, Rect2;
    private SquareArea x1, x2, x3, x4, x5, x6;
    private float w = 1200, h = 700;
    private int valor = 1;
    private boolean flag_save = false, flag_pintar = false;
    int check1 = 0, check2 = 0, check3 = 0, check4 = 0, check5 = 0, check6 = 0;
    private long tiempo1 = 0, tiempo2 = 0;
    long endTime= 0, initialTime = 0, totalTime = 0;
    VelocityTracker tracker = null;
    static float MaxVelocity_x = 0;
    static float MaxVelocity_y = 0;
    int fail = 0;
    int color;
    //MainActivity main = new MainActivity();


    public Screen_4(Context context, Activity activity) {
        super(context);
        newActivity = activity;
        init(context);

    }

    public Screen_4(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }

    public Screen_4(Context context, AttributeSet attrs, int defStyleAttr) {
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


    private Paint mCirclePaint;
    private Paint Circle_stroke;
    private Paint mSquarePaint;
    private Paint red_stroke, blue_stroke;
    private Paint mRectPaint;
    private Paint Rect_stroke;
    private Paint mFondoPaint;

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


        mSquarePaint = new Paint();
        mSquarePaint.setColor(Color.GREEN);
        mSquarePaint.setStyle(Paint.Style.FILL);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.BLUE);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mRectPaint = new Paint();
        mRectPaint.setColor(Color.RED);
        mRectPaint.setStyle(Paint.Style.FILL);


        mFondoPaint = new Paint();
        mFondoPaint.setColor(Color.WHITE);
        mFondoPaint.setStyle(Paint.Style.FILL);

        red_stroke = new Paint();
        red_stroke.setStyle(Paint.Style.STROKE);
        red_stroke.setStrokeWidth(5);
        red_stroke.setColor(Color.RED);

        blue_stroke = new Paint();
        blue_stroke.setStyle(Paint.Style.STROKE);
        blue_stroke.setStrokeWidth(5);
        blue_stroke.setColor(Color.BLUE);

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
       // canv.drawCircle(200, 200, 180, mFondoPaint);
       // canv.drawCircle(200, 200, 180, Circle_stroke);

        //cuadrados fijos.
        canv.drawRect(50, 150, 150, 250, mFondoPaint);
        canv.drawRect(50, 150, 150, 250, red_stroke);
        canv.drawRect(250, 150, 400, 300, mFondoPaint);
        canv.drawRect(250, 150, 400, 300, red_stroke);
        canv.drawRect(500, 150, 700, 350, mFondoPaint);
        canv.drawRect(500, 150, 700, 350, red_stroke);

        canv.drawRect(50, 400, 170, 520, mFondoPaint);
        canv.drawRect(50, 400, 170, 520, blue_stroke);
        canv.drawRect(250, 400, 420, 570, mFondoPaint);
        canv.drawRect(250, 400, 420, 570, blue_stroke);
        canv.drawRect(500, 400, 720, 620, mFondoPaint);
        canv.drawRect(500, 400, 720, 620, blue_stroke);


        Rect_stroke = new Paint();
        Rect_stroke.setStyle(Paint.Style.STROKE);
        Rect_stroke.setStrokeWidth(5);
        Rect_stroke.setColor(Color.RED);

       // canv.drawCircle(1200, 200, 120, mFondoPaint);
       // canv.drawCircle(1200, 200, 120, Rect_stroke);


        //Texto indicativo.
        /*Paint paintText = new Paint();
        paintText.setTextSize(40);
        paintText.setColor(Color.BLACK);
        paintText.setStrokeWidth(7);
        String texto = "COLOQUE LAS FICHAS";
        canv.drawText(texto, 500, 500, paintText);*/

        //imagen boton de checkeo
        if(flag_pintar == false)
            canv.drawBitmap(save_Bitmap, 1060, 20, null);

        canv.drawBitmap(next_Bitmap, 1160, 20, null);
        canv.drawBitmap(speak_Bitmap, 120, 20, null);
        canv.drawBitmap(home_Bitmap, 20, 20, null);


        // Posición inicial de figuras dinámicas

        x1 = obtainTouchedSquare(1000, 500);
        x2 = obtainTouchedSquare(1000, 200);
        x3 = obtainTouchedSquare(900, 500);
        x4 = obtainTouchedSquare(800, 300);
        x5 = obtainTouchedSquare(900, 350);
        x6 = obtainTouchedSquare(1000, 400);


        for (SquareArea square : mSquare) {
            if(square.num == 1)
                canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, mRectPaint);
            else if(square.num == 2)
                canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, mCirclePaint);
            else if(square.num == 3)
                canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, mRectPaint);
            else if(square.num == 4)
                canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, mCirclePaint);
            else if(square.num == 5)
                canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, mRectPaint);
            else
                canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, mCirclePaint);
        }

    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        boolean handled = false;

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

                    touchedSquare = mSquarePointer.get(pointerId);



                    if (null != touchedSquare) {
                        touchedSquare.leftX = xTouch;
                        touchedSquare.leftY = yTouch;

                    }

                    if(touchedSquare.num == 1)
                    {
                        if((touchedSquare.leftX > 30) && (touchedSquare.leftX < 70)& (touchedSquare.leftY > 130)&&(touchedSquare.leftY < 170)){
                            check1 = 1;
                            touchedSquare.leftX = 50;
                            touchedSquare.leftY = 150;
                        }


                    }

                    if(touchedSquare.num == 2)
                    {
                        if((touchedSquare.leftX > 30) && (touchedSquare.leftX < 70) && (touchedSquare.leftY > 380) && (touchedSquare.leftY < 420)) {
                            check2 = 1;
                            touchedSquare.leftX = 50;
                            touchedSquare.leftY = 400;
                        }

                    }

                    if(touchedSquare.num == 3)
                    {
                        if((touchedSquare.leftX > 230) && (touchedSquare.leftX < 270) && (touchedSquare.leftY > 130) && (touchedSquare.leftY < 170)) {
                            check3 = 1;
                            touchedSquare.leftX = 250;
                            touchedSquare.leftY = 150;
                        }

                    }

                    if(touchedSquare.num == 4)
                    {
                        if((touchedSquare.leftX > 230) && (touchedSquare.leftX < 270) && (touchedSquare.leftY > 380) && (touchedSquare.leftY < 420)) {
                            check4 = 1;
                            touchedSquare.leftX = 250;
                            touchedSquare.leftY = 400;
                        }

                    }

                    if(touchedSquare.num == 5)
                    {
                        if((touchedSquare.leftX > 480) && (touchedSquare.leftX < 520) && (touchedSquare.leftY > 130) && (touchedSquare.leftY < 170)) {
                            check5 = 1;
                            touchedSquare.leftX = 500;
                            touchedSquare.leftY = 150;
                        }

                    }

                    if(touchedSquare.num == 6)
                    {
                        if((touchedSquare.leftX > 480) && (touchedSquare.leftX < 520) && (touchedSquare.leftY > 380) && (touchedSquare.leftY < 420)) {
                            check6 = 1;
                            touchedSquare.leftX = 500;
                            touchedSquare.leftY = 400;
                        }

                    }

                   /* if(touchedCircle.radius == 180) {
                        //comprobamos que el circulo pulsado se situa en la posicion correcta.
                        if ((touchedCircle.centerX > 190) && (touchedCircle.centerX < 210) && (touchedCircle.centerY > 190) && (touchedCircle.centerY < 210)) {
                            Log.w(TAG, "circulo 1");

                            //if(check1 == 0)
                            check1 = 1;

                        } else
                            check1 = 0;
                    }

                    if(touchedCircle.radius == 120) {

                        if ((touchedCircle.centerX > 1190) && (touchedCircle.centerX < 1210) && (touchedCircle.centerY > 190) && (touchedCircle.centerY < 210)) {
                            Log.w(TAG, "circulo 2");

                            check2 = 1;

                        } else
                            check2 = 0;
                    }*/



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
                    String num4 = Integer.toString(check5);
                    String num5 = Integer.toString(check6);
                    Log.w(num, "valor check1");
                    Log.w(num1, "valor check2");
                    Log.w(num2, "valor check3");
                    Log.w(num3, "valor check4");
                    Log.w(num4, "valor check5");
                    Log.w(num5, "valor check6");

                    //check = Comprobar();
                    if ((check1 == 1) && (check2 == 1) && (check3 == 1) && (check4 == 1) && (check5 == 1) && (check6 == 1)) {

                        Log.w(TAG, "funcionando");

                        tiempo2 = System.currentTimeMillis();
                        tiempo2 = tiempo2 - tiempo1;
                        registros.setTiempo(tiempo2);

                        mostrar.set_fallos(fail);
                        mostrar.set_nivel(2);
                        registros.setPuzzle(4);



                        Intent intent = new Intent(getContext(), Level.class);
                        newActivity.startActivity(intent);


                        //animation.start();
                        // playActivity.setContentView();
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
                        check6 = 0;
                        mSquare.clear();
                        x1 = obtainTouchedSquare(1000, 100);
                        x2 = obtainTouchedSquare(1000, 200);
                        x3 = obtainTouchedSquare(900, 100);
                        x4 = obtainTouchedSquare(800, 300);
                        x5 = obtainTouchedSquare(900, 200);
                        x6 = obtainTouchedSquare(1000, 400);
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
                    touchedCircle = new CircleArea(xTouch, yTouch, 120, 0);
                    valor = 2;
                    break;

                case 2:
                    touchedCircle = new CircleArea(xTouch, yTouch, 180, 0);
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
            //touchedSquare = new SquareArea(100, 100, xTouch, yTouch);
            // touchedSquare = new SquareArea(200, 200, xTouch, yTouch);
            //touchedSquare = new SquareArea(200, 200, xTouch, yTouch);

            switch(valor) {

                case 1:
                    touchedSquare = new SquareArea(100, 100, xTouch, yTouch, 1);
                    valor = 2;
                    break;

                case 2:
                    touchedSquare= new SquareArea(120, 120, xTouch, yTouch, 2);
                    valor = 3;
                    break;

                case 3:
                    touchedSquare= new SquareArea(150, 150, xTouch, yTouch, 3);
                    valor = 4;
                    break;

                case 4:
                    touchedSquare= new SquareArea(170, 170, xTouch, yTouch, 4);
                    valor = 5;
                    break;

                case 5:
                    touchedSquare= new SquareArea(200, 200, xTouch, yTouch, 5);
                    valor = 6;
                    break;

                case 6:
                    touchedSquare= new SquareArea(220, 220, xTouch, yTouch, 6);
                    valor = 1;
                    break;


            }


            if (mSquare.size() < 6) {
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



 /*   private int Comprobar()
    {
        Log.w(TAG, "Comprobando");
        int numCheck = 0;

        if(AreaCorrecta()== true);
        {
            Log.w(TAG, "Area correcta");
            numCheck = 2;
        }

        return numCheck;

    }

    private boolean AreaCorrecta() {
        Log.w(TAG, "Corrigiendo");

        if ((x1.centerX > 190) && (x1.centerX < 210) && (x1.centerY > 190) && (x1.centerY < 210)){ //&& ((x2.centerX > 1190) && (x2.centerX < 1210) && (x2.centerY > 190) && (x2.centerY < 210))) {
                Log.w(TAG, "circulo 2");
                return true;
            }
        else
            return false;

    }*/



}
