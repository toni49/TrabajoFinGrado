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



    // Activity de la clase Play
    private Activity newActivity = null;

    /** Main bitmap */
    private Bitmap next_Bitmap = null, pause_Bitmap = null;


    private Rect Rect1, Rect2;
    private SquareArea s1, s2;
    private CircleArea x1, x2;
    private float w, h;
    int check1 = 0, check2 = 0, check3 = 0, check4 = 0;
    int fail = 0;
    Mostrar_nivel mostrar = new Mostrar_nivel();

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

                setBackgroundResource(R.drawable.madera_1);

                canv.drawCircle(150, 150, 100, whitePaint);
                canv.drawCircle(150, 150, 100, blue_Stroke);

                canv.drawCircle(750, 150, 100, whitePaint);
                canv.drawCircle(750, 150, 100, yellow_Stroke);

                canv.drawRect(350, 60, 550, 260, whitePaint);
                canv.drawRect(350, 60, 550, 260, red_stroke);

                canv.drawRect(950, 60, 1150, 260, whitePaint);
                canv.drawRect(950, 60, 1150, 260, green_Stroke);



                //Texto indicativo.
               /* Paint paintText = new Paint();
                paintText.setTextSize(50);
                paintText.setColor(Color.BLACK);
                paintText.setStrokeWidth(7);
                String texto = "COLOQUE LAS FICHAS";
                canv.drawText(texto, 750, 800, paintText);*/

                //imagen boton de checkeo

                canv.drawBitmap(next_Bitmap, 1170, 600, null);
                canv.drawBitmap(pause_Bitmap, 70, 600, null);



                // Posición inicial de figuras dinámicas

                s1 = obtainTouchedSquare(950, 400);
                s2 = obtainTouchedSquare(900, 400);
                x1 = obtainTouchedCircle(600, 400);
                x2 = obtainTouchedCircle(700, 500);


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

                CircleArea touchedCircle;
                SquareArea touchedSquare;

                int xTouch;
                int yTouch;
                int pointerId;
                int actionIndex = event.getActionIndex();



                // get touch event coordinates and make transparent circle from it
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:

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


                    case MotionEvent.ACTION_MOVE:
                        final int pointerCount = event.getPointerCount();

                        Log.w(TAG, "Move");

                        for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
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

                                if ((touchedCircle.centerX > 730) && (touchedCircle.centerX < 770) && (touchedCircle.centerY > 130) && (touchedCircle.centerY < 170))
                                    check3 = 1;

                            }

                            if (touchedCircle.num == 2) {

                                if ((touchedCircle.centerX > 130) && (touchedCircle.centerX < 170) && (touchedCircle.centerY > 130) && (touchedCircle.centerY < 170))
                                    check1 = 1;

                            }


                            if (touchedSquare.num == 3) {

                                if ((touchedSquare.leftX > 330) && (touchedSquare.leftX < 370) && (touchedSquare.leftY > 30) && (touchedSquare.leftY < 90))
                                    check2 = 1;


                            }

                            if (touchedSquare.num == 4) {

                                if ((touchedSquare.leftX > 930) && (touchedSquare.leftX < 980) && (touchedSquare.leftY > 30) && (touchedSquare.leftY < 90))
                                    check4 = 1;

                            }



                        }
                        invalidate();
                        handled = true;
                        break;

                    case MotionEvent.ACTION_UP:
                        xTouch = (int) event.getX(0);
                        yTouch = (int) event.getY(0);

                        if ((xTouch > 1120) && (xTouch < 1220) && (yTouch > 560) && (yTouch < 640)) {
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
                                check1 = 0;
                                check2 = 0;
                                check3 = 0;
                                check4 = 0;

                                Log.w(TAG, "funcionando");
                                mostrar.set_fallos(fail);
                                //mostrar.set_nivel(2);

                                Screen_3 screen_3 = new Screen_3(getContext(), newActivity);
                                newActivity.setContentView(screen_3);


                                //Intent intent = new Intent(getContext(), Level.class);
                                //newActivity.startActivity(intent);

                            }
                            else {
                                fail = fail + 1;  //aumentamos la variable fail en caso de no acertar puzzle.
                                String fallo = Integer.toString(fail);
                                Log.w(fallo, "numero de fallos");
                                mCircles.clear();
                                mSquare.clear();
                                s1 = obtainTouchedSquare(950, 400);
                                s2 = obtainTouchedSquare(900, 400);
                                x1 = obtainTouchedCircle(600, 400);
                                x2 = obtainTouchedCircle(700, 500);
                                invalidate();
                            }

                            // playActivity.setContentView();

                        }

                        else if ((xTouch > 30) && (xTouch < 110) && (yTouch > 560) && (yTouch < 640)) {
                            Log.w(TAG, "PULSADO PAUSE");

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




