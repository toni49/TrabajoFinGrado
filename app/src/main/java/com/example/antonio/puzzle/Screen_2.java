package com.example.antonio.puzzle;

import android.app.Activity;
import android.content.Context;
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



    // Activity de la clase Play
    private Activity newActivity = null;

    /** Main bitmap */
    private Bitmap sBitmap = null;
    private Bitmap Bitma = null;


    private Rect Rect1, Rect2;
    private SquareArea s1, s2;
    private CircleArea x1, x2, x3;
    private float w, h;
    int check;

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
        int centerX;
        int centerY;

        SquareArea(int Swidth, int Sheight, int leftX, int leftY) {
            this.Swidth = Swidth;
            this.Sheight = Sheight;
            this.leftX = leftX;
            this.leftY = leftY;
            this.centerX = leftX + Swidth/2;
            this.centerY = leftY + Sheight/2;
        }
    }




       /* @Override
              public String toString() {
            return "Circle[" + centerX + ", " + centerY + ", " + radius + "]";
        }
    }*/


            /**
             * Paint to draw circles
             */
            private Paint mCirclePaint;
            private Paint Circle_stroke;
            private Paint mSquarePaint;
            private Paint Square_stroke;
            private Paint mRectPaint;
            private Paint Rect_stroke;
            private Paint mFondoPaint;

            //private final Random mRadiusGenerator = new Random();
            // Radius limit in pixels
            //private final static int RADIUS_LIMIT = 100;

            private static final int CIRCLES_LIMIT = 2;

            /**
             * All available circles
             */
            private HashSet<CircleArea> mCircles = new HashSet<CircleArea>(CIRCLES_LIMIT);
            private SparseArray<CircleArea> mCirclePointer = new SparseArray<CircleArea>(CIRCLES_LIMIT);

            private HashSet<SquareArea> mSquare = new HashSet<SquareArea>(CIRCLES_LIMIT);
            private SparseArray<SquareArea> mSquarePointer = new SparseArray<SquareArea>(CIRCLES_LIMIT);


            /**
             * Default constructor
             *
             * @param context {@link android.content.Context}
             */


            private void init(final Context context) {
                // Generate bitmap used for background

                sBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.success_64);

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
                // background bitmap to cover all area

                //DrawFlag = 1;

                Circle_stroke = new Paint();
                Circle_stroke.setStyle(Paint.Style.STROKE);
                Circle_stroke.setStrokeWidth(5);
                Circle_stroke.setColor(Color.BLUE);
                setBackgroundResource(R.drawable.madera_1);

                //Cirulos fijos
                canv.drawCircle(200, 200, 100, mFondoPaint);
                canv.drawCircle(200, 200, 100, Circle_stroke);

                canv.drawCircle(1200, 200, 100, mFondoPaint);
                canv.drawCircle(1200, 200, 100, Circle_stroke);
                //canv.drawCircle(900, 200, 100, Circle_stroke);
                //canv.drawCircle(1500, 200, 100, Circle_stroke);

                //cuadrados, rectangulos fijos.
                Rect_stroke = new Paint();
                Rect_stroke.setStyle(Paint.Style.STROKE);
                Rect_stroke.setStrokeWidth(5);
                Rect_stroke.setColor(Color.RED);
                canv.drawRect(550,110, 750, 310, mFondoPaint);
                canv.drawRect(550,110, 750, 310, Rect_stroke);

                canv.drawRect(1600,110, 1800, 310, mFondoPaint);
                canv.drawRect(1600,110, 1800, 310, Rect_stroke);



                //Texto indicativo.
                Paint paintText = new Paint();
                paintText.setTextSize(50);
                paintText.setColor(Color.BLACK);
                paintText.setStrokeWidth(5);
                String texto = "COLOQUE LAS FICHAS";
                canv.drawText(texto, 750, 800, paintText);

                //imagen boton de checkeo
                w = 1740;
                h = 790;
                canv.drawBitmap(sBitmap, w, h, null);


                // Posición inicial de figuras dinámicas

                s1 = obtainTouchedSquare(200, 600);
                s2 = obtainTouchedSquare(500, 600);
                x1 = obtainTouchedCircle(1400, 700);
                x2 = obtainTouchedCircle(1700, 700);


                for (SquareArea square: mSquare) {
                    int w = square.leftX + square.Swidth;
                    int h = square.leftY + square.Sheight;
                    canv.drawRect(square.leftX, square.leftY, w, h, mRectPaint);
                }


                for (CircleArea circle : mCircles) {
                    canv.drawCircle(circle.centerX, circle.centerY, circle.radius, mCirclePaint);
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
                            if ((touchedCircle.centerX > 190) && (touchedCircle.centerX < 210) && (touchedCircle.centerY > 190) && (touchedCircle.centerY < 210)) {
                                Log.w(TAG, "circulo 1");
                                check = 1;
                            }
                            if ((touchedCircle.centerX > 1190) && (touchedCircle.centerX < 1210) && (touchedCircle.centerY > 190) && (touchedCircle.centerY < 210)) {
                                check = 2;
                                Log.w(TAG, "circulo 2");
                            }

                            if ((touchedSquare.leftX > 540) && (touchedSquare.leftX < 560) && (touchedSquare.leftY > 100) && (touchedSquare.leftY < 120)) {
                                Log.w(TAG, "cuadrado 1");
                                check = 3;
                            }
                            if ((touchedSquare.leftX > 1590) && (touchedSquare.leftX < 1610) && (touchedSquare.leftY > 100) && (touchedSquare.leftY < 120)) {
                                check = 4;
                                Log.w(TAG, "cuadrado 2");
                            }


                        }
                        invalidate();
                        handled = true;
                        break;

                    case MotionEvent.ACTION_UP:
                        xTouch = (int) event.getX(0);
                        yTouch = (int) event.getY(0);

                        if ((xTouch > 1720) && (xTouch < 1880) && (yTouch > 800) && (yTouch < 940)) {
                            Log.w(TAG, "PULSADO");
                            String num = Integer.toString(check);
                            Log.w(num, "valor check");


                            //check = Comprobar();
                            if (check == 4) {
                                check = 0;
                                Log.w(TAG, "funcionando");
                                //animation.start();

                                //Pasar a siguiente nivel.
                            }

                            // playActivity.setContentView();

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
            touchedCircle = new CircleArea(xTouch, yTouch, 100);



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
            touchedSquare = new SquareArea(200, 200, xTouch, yTouch);


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
            if ((((square.leftX + square.Swidth) > xTouch) && ((square.leftX) < xTouch)) && (((square.leftY + square.Sheight) > yTouch) && ((square.leftY) < yTouch)))
            {
                Log.w(TAG, "cuadrado tocado" );

                touched = square;
                break;
            }
        }

        return touched;
    }



   /* @Override
     protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mMeasuredRect = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }*/

            //Pulsar un boton cuando se quiera hacer la comprobación de las piezas.
    private int Comprobar()
    {
        int numCheck = 0;
        Log.w(TAG, "Comprobando");


        if(AreaCorrecta());
        {
            Log.w(TAG, "Area correcta");
            numCheck = +1;
        }

        return numCheck;

    }

    private boolean AreaCorrecta()
    {
        //Log.w(TAG, "Corrigiendo");



        if((x2.centerX > 590) && (x2.centerX > 610 ) && (x2.centerY > 190) && (x2.centerY < 210))
            return true;
        if((x3.centerX > 890) && (x3.centerX > 910 ) && (x3.centerY > 190) && (x3.centerY < 210))
            return true;
        else {
            Log.w(TAG, "check 0");
            return false;
        }
    }

}

   /* private int Comprobar()
    {
        int numCheck = 0;
        Log.w(TAG, "Comprobando");


        if((x1.centerX > 290) && (x1.centerX > 310 ) && (x1.centerY > 190) && (x1.centerY < 210)) {
            Log.w(TAG, "check 1");
            numCheck =+1;
        }

        /*else if((x2.centerX > 590) && (x2.centerX > 610 ) && (x2.centerY > 190) && (x2.centerY < 210))
            Log.w(TAG, "check 2");

        else if((x3.centerX > 890) && (x3.centerX > 910 ) && (x3.centerY > 190) && (x3.centerY < 210))
            Log.w(TAG, "check 3");

        else
            Log.w(TAG, "check 0");

        return numCheck;
    }*/




