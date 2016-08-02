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
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

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
    private Bitmap pause_Bitmap = null;
    private Bitmap Bitma = null;


    private Rect Rect1, Rect2;
    private CircleArea x1, x2, x3;
    private float w, h;
    private int valor = 1;
    int check1 = 0;
    int check2 = 0;
    MainActivity main = new MainActivity();


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


    private Paint mCirclePaint;
    private Paint Circle_stroke;
    private Paint mSquarePaint;
    private Paint Square_stroke;
    private Paint mRectPaint;
    private Paint Rect_stroke;
    private Paint mFondoPaint;

    private static final int CIRCLES_LIMIT = 2;

    private HashSet<CircleArea> mCircles = new HashSet<CircleArea>(CIRCLES_LIMIT);
    private SparseArray<CircleArea> mCirclePointer = new SparseArray<CircleArea>(CIRCLES_LIMIT);



    private void init(final Context context) {

        next_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.next);
        pause_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pause);


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

    }

    @Override
    public void onDraw(final Canvas canv) {

        Circle_stroke = new Paint();
        Circle_stroke.setStyle(Paint.Style.STROKE);
        Circle_stroke.setStrokeWidth(5);
        Circle_stroke.setColor(Color.BLUE);
        setBackgroundResource(R.drawable.madera_1);

        //Cirulos fijos
        canv.drawCircle(200, 200, 180, mFondoPaint);
        canv.drawCircle(200, 200, 180, Circle_stroke);

        Rect_stroke = new Paint();
        Rect_stroke.setStyle(Paint.Style.STROKE);
        Rect_stroke.setStrokeWidth(5);
        Rect_stroke.setColor(Color.RED);

        canv.drawCircle(1200, 200, 120, mFondoPaint);
        canv.drawCircle(1200, 200, 120, Rect_stroke);


        //Texto indicativo.
        Paint paintText = new Paint();
        paintText.setTextSize(50);
        paintText.setColor(Color.BLACK);
        paintText.setStrokeWidth(5);
        String texto = "COLOQUE LAS FICHAS";
        canv.drawText(texto, 750, 900, paintText);

        //imagen boton de checkeo
       // w = 1740;
      //  h = 790;
        canv.drawBitmap(next_Bitmap, 1740, 790, null);

        canv.drawBitmap(pause_Bitmap, 70, 790, null);


        // Posición inicial de figuras dinámicas

        x1 = obtainTouchedCircle(1000, 600);
        x2 = obtainTouchedCircle(1400, 600);


        for (CircleArea circle : mCircles) {
            if(circle.radius==180)
                canv.drawCircle(circle.centerX, circle.centerY, 180, mCirclePaint);
            else
                canv.drawCircle(circle.centerX, circle.centerY, 120, mRectPaint);

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


            case MotionEvent.ACTION_MOVE:
                final int pointerCount = event.getPointerCount();

                Log.w(TAG, "Move");

                for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
                    // Some pointer has moved, search it by pointer id
                    pointerId = event.getPointerId(actionIndex);

                    xTouch = (int) event.getX(actionIndex);
                    yTouch = (int) event.getY(actionIndex);

                    touchedCircle = mCirclePointer.get(pointerId);



                    if (null != touchedCircle) {
                        touchedCircle.centerX = xTouch;
                        touchedCircle.centerY = yTouch;

                    }


                    if(touchedCircle.radius == 180) {
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
                    }



                }
                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_UP:
                xTouch = (int) event.getX(0);
                yTouch = (int) event.getY(0);

                if ((xTouch > 1720) && (xTouch < 1880) && (yTouch > 780) && (yTouch < 940)) {
                    Log.w(TAG, "PULSADO NEXT");
                    String num = Integer.toString(check1);
                    String num1 = Integer.toString(check2);
                    Log.w(num, "valor check1");
                    Log.w(num1, "valor check2");

                    //check = Comprobar();
                    if ((check1 == 1) && (check2 == 1)) {
                        check1 = 0;
                        check2 = 0;
                        Log.w(TAG, "funcionando");
                        mCircles.clear();       //Las piezas se borran y se vuelven a dibujar en la posicion exacto, creando un efecto de colocación.
                        x1 = obtainTouchedCircle(1200, 200);
                        x2 = obtainTouchedCircle(200, 200);
                       // x1 = obtainTouchedCircle(1200, 200);


                        //animation.start();
                        // playActivity.setContentView();
                    }
                }

                else if ((xTouch > 40) && (xTouch < 200) && (yTouch > 760) && (yTouch < 940)) {
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

                invalidate();
                handled = true;
                break;


            case MotionEvent.ACTION_CANCEL:
                handled = true;
                break;

            default:
                // do nothing
                break;
        }

        return super.onTouchEvent(event) || handled;
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


