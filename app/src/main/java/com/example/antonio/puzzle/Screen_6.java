package com.example.antonio.puzzle;

/**
 * Created by antonio on 8/12/16.
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
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashSet;
import java.util.Random;


public class Screen_6 extends View {

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

    private CircleArea x1, x2, x3;
    private float w, h;
    private int valor = 1;
    int check1 = 0, check2 = 0, check3 = 0;
    int fail = 0;
    Mostrar_nivel mostrar = new Mostrar_nivel();
    MainActivity main = new MainActivity();


    public Screen_6(Context context, Activity activity) {
        super(context);
        newActivity = activity;

        init(context);

    }

    public Screen_6(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }

    public Screen_6(Context context, AttributeSet attrs, int defStyleAttr) {
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


    private Paint greenPaint;
    private Paint stroke_blue;
    private Paint redPaint;
    private Paint stroke_yellow;
    private Paint mRectPaint;
    private Paint bluePaint;
    private Paint mFondoPaint;
    private Paint yellowPaint;

    private static final int CIRCLES_LIMIT = 4;

    private HashSet<CircleArea> mCircles = new HashSet<CircleArea>(CIRCLES_LIMIT);
    private SparseArray<CircleArea> mCirclePointer = new SparseArray<CircleArea>(CIRCLES_LIMIT);



    private void init(final Context context) {

        next_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.next);
        pause_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pause);


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


        mFondoPaint = new Paint();
        mFondoPaint.setColor(Color.WHITE);
        mFondoPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    public void onDraw(final Canvas canv) {

        stroke_blue = new Paint();
        stroke_blue.setStyle(Paint.Style.STROKE);
        stroke_blue.setStrokeWidth(7);
        stroke_blue.setColor(Color.BLUE);
        setBackgroundResource(R.drawable.madera_1);

        //Cirulos fijos
        canv.drawCircle(400, 200, 140, mFondoPaint);
        canv.drawCircle(400, 200, 140, stroke_blue);

        canv.drawCircle(1000, 200, 120, mFondoPaint);
        canv.drawCircle(1000, 200, 120, stroke_blue);

        canv.drawCircle(1600, 200, 100, mFondoPaint);
        canv.drawCircle(1600, 200, 100, stroke_blue);


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
        x3 = obtainTouchedCircle(600, 600);
        x3 = obtainTouchedCircle(200, 600);



        for (CircleArea circle : mCircles) {
            if(circle.radius==140)
                canv.drawCircle(circle.centerX, circle.centerY, 140, bluePaint);
            else if(circle.radius == 120)
                canv.drawCircle(circle.centerX, circle.centerY, 120, bluePaint);
            else if(circle.radius == 110)
                canv.drawCircle(circle.centerX, circle.centerY, 110, bluePaint);
            else
                canv.drawCircle(circle.centerX, circle.centerY, 100, bluePaint);


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


                    if(touchedCircle.radius == 140) {
                        //comprobamos que el circulo pulsado se situa en la posicion correcta.
                        if ((touchedCircle.centerX > 390) && (touchedCircle.centerX < 410) && (touchedCircle.centerY > 190) && (touchedCircle.centerY < 210)) {
                            Log.w(TAG, "circulo 1");

                            //if(check1 == 0)
                            check1 = 1;

                        } else
                            check1 = 0;
                    }

                    if(touchedCircle.radius == 120) {

                        if ((touchedCircle.centerX > 990) && (touchedCircle.centerX < 1010) && (touchedCircle.centerY > 190) && (touchedCircle.centerY < 210)) {
                            Log.w(TAG, "circulo 2");

                            check2 = 1;

                        } else
                            check2 = 0;
                    }

                    if(touchedCircle.radius == 100) {

                        if ((touchedCircle.centerX > 1590) && (touchedCircle.centerX < 1610) && (touchedCircle.centerY > 190) && (touchedCircle.centerY < 210)) {
                            Log.w(TAG, "circulo 2");

                            check3 = 1;

                        } else
                            check3 = 0;
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
                    String num2 = Integer.toString(check3);

                    Log.w(num, "valor check1");
                    Log.w(num1, "valor check2");
                    Log.w(num2, "valor check3");


                    //check = Comprobar();
                    if ((check1 == 1) && (check2 == 1) && (check3 == 1)) {
                        check1 = 0;
                        check2 = 0;
                        check3 = 0;
                        mostrar.set_fallos(fail);
                        mostrar.set_nivel(2);
                        Log.w(TAG, "funcionando");


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
                        invalidate();
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
                    touchedCircle = new CircleArea(xTouch, yTouch, 120);
                    valor = 2;
                    break;

                case 2:
                    touchedCircle = new CircleArea(xTouch, yTouch, 140);
                    valor = 3;
                    break;

                case 3:
                    touchedCircle = new CircleArea(xTouch, yTouch, 100);
                    valor = 4;
                    break;

                case 4:
                    touchedCircle = new CircleArea(xTouch, yTouch, 110);
                    valor = 1;
                    break;


            }

            if (mCircles.size() < 4) {
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

