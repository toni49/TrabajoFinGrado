package com.example.antonio.puzzle;

import android.app.Activity;
import android.content.Context;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by antonio on 7/21/16.
 */
public class CirclesDrawingView extends View {

    private static final String TAG = "CirclesDrawingView";
    private static int DrawFlag = 0;
    private Button botonIni;
    public Canvas c1;


  /*  Thread shapes = new Thread(new Runnable(){

        @Override
        public void run() {
            Log.w(TAG, "RUN");

            Paint text;
            text = new Paint();


            while (true) {
                if (DrawFlag == 1) {
                   // check = Comprobar();
                    if (check == 3) {
                        //Animacion o texto indicando que el puzzle esta correcto.
                        text.setTextSize(14);
                        c1.drawText("Correcto", 700, 500, text);
                        playActivity.setContentView(R.layout.play);

                    }

                }


            }
        }

    });*/




    // Activity de la clase Play
    private Activity playActivity = null;

    /** Main bitmap */
    private Bitmap mBitmap = null;

    private Rect Rect1, Rect2;
    private CircleArea x1, x2, x3;
    private float w, h;
    int check;


    /** Stores data about single circle */
    public static class CircleArea {
        int radius;
        int centerX;
        int centerY;

        CircleArea(int centerX, int centerY, int radius) {
            this.radius = radius;
            this.centerX = centerX;
            this.centerY = centerY;
        }

        @Override
        public String toString() {
            return "Circle[" + centerX + ", " + centerY + ", " + radius + "]";
        }
    }




    /** Paint to draw circles */
    private Paint mCirclePaint;
    private Paint Circle_stroke;

    //private final Random mRadiusGenerator = new Random();
    // Radius limit in pixels
    //private final static int RADIUS_LIMIT = 100;

    private static final int CIRCLES_LIMIT = 3;

    /** All available circles */
    private HashSet<CircleArea> mCircles = new HashSet<CircleArea>(CIRCLES_LIMIT);
    private SparseArray<CircleArea> mCirclePointer = new SparseArray<CircleArea>(CIRCLES_LIMIT);

    /**
     * Default constructor
     *
     * @param ct {@link android.content.Context}
     */
    public CirclesDrawingView(final Context ct, Activity mainActivity) {
        super(ct);

        playActivity = mainActivity; // Creamos activity play para poder retroceder a la activity padre.
        init(ct);
    }

    public CirclesDrawingView(final Context ct, final AttributeSet attrs) {
        super(ct, attrs);
        init(ct);
    }

    public CirclesDrawingView(final Context ct, final AttributeSet attrs, final int defStyle) {
        super(ct, attrs, defStyle);
        init(ct);
    }




    private void init(final Context ct) {
        // Generate bitmap used for background

        mBitmap = BitmapFactory.decodeResource(ct.getResources(), R.drawable.success_64);
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.BLUE);
        mCirclePaint.setStrokeWidth(40);
        mCirclePaint.setStyle(Paint.Style.FILL);

    }

    @Override
    public void onDraw(final Canvas canv) {
        // background bitmap to cover all area

        DrawFlag = 1;

        Circle_stroke = new Paint();
        Circle_stroke.setStyle(Paint.Style.STROKE);
        Circle_stroke.setStrokeWidth(5);
        Circle_stroke.setColor(Color.BLUE);
        setBackgroundResource(R.drawable.madera_1);


        //Cirulos fijos
        canv.drawCircle(300, 200, 100,  Circle_stroke);
        canv.drawCircle(900, 200, 100,  Circle_stroke);
        canv.drawCircle(1500, 200, 100,  Circle_stroke);
        //canv.drawRect(1200,200, 1400, 320, Circle_stroke);
        //canv.drawRect(1600,200, 1800, 320, Circle_stroke);



        w = 1740;
        h = 790;
        canv.drawBitmap(mBitmap, w, h, null);


        // Posición inicial de circulos dinámicos
        x1 = obtainTouchedCircle(100, 600);
        x2 = obtainTouchedCircle(250, 600);
        x3 = obtainTouchedCircle(400, 600);



        for (CircleArea circle : mCircles) {
            canv.drawCircle(circle.centerX, circle.centerY, circle.radius, mCirclePaint);
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
                // it's the first pointer, so clear all existing pointers data
               // clearCirclePointer();
                xTouch = (int) event.getX(0);
                yTouch = (int) event.getY(0);

                // check if we've touched inside some circle
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

                    //comprobamos que el circulo pulsado se situa en la posicion correcta.
                    if((touchedCircle.centerX > 290) && (touchedCircle.centerX < 310 ) && (touchedCircle.centerY > 190) && (touchedCircle.centerY < 210)) {
                        Log.w(TAG, "circulo 1");
                        check = 1;
                    }
                    if((touchedCircle.centerX > 890) && (touchedCircle.centerX < 910 ) && (touchedCircle.centerY > 190) && (touchedCircle.centerY < 210)) {
                        check = 2;
                        Log.w(TAG, "circulo 2");
                    }
                    if((touchedCircle.centerX > 1490) && (touchedCircle.centerX < 1510 ) && (touchedCircle.centerY > 190) && (touchedCircle.centerY < 210))
                    {
                        check = 3;
                        Log.w(TAG, "check 3");
                    }

                }
                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_UP:
                xTouch = (int) event.getX(0);
                yTouch = (int) event.getY(0);

                if((xTouch > 800) && (xTouch < 1200) && (yTouch > 500) && (yTouch < 800)) {
                    Log.w(TAG, "PULSADO");
                    String num= Integer.toString(check);
                    Log.w(num, "valor check");


                    //check = Comprobar();
                    if (check == 3) {
                        check = 0;
                        Log.w(TAG, "funcionando");

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
     *
     * @return obtained {@link CircleArea}
     */
    private CircleArea obtainTouchedCircle(final int xTouch, final int yTouch) {
        CircleArea touchedCircle = getTouchedCircle(xTouch, yTouch);

        if (null == touchedCircle) {
            touchedCircle = new CircleArea(xTouch, yTouch, 100);

            //touchedCircle = new CircleArea(xTouch, yTouch, mRadiusGenerator.nextInt(RADIUS_LIMIT) + RADIUS_LIMIT);

          /*  if (mCircles.size() == CIRCLES_LIMIT) {
                Log.w(TAG, "Clear all circles, size is " + mCircles.size());
                //remove first circle
                mCircles.clear();
            }*/

            if(mCircles.size()<3) {
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
     *
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

   /* @Override
     protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mMeasuredRect = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }*/

    //Pulsar un boton cuando se quiera hacer la comprobación de las piezas.
 /*   private int Comprobar()
    {
        int numCheck = 0;
        Log.w(TAG, "Comprobando");


        if(AreaCorrecta());
        {
            Log.w(TAG, "Area correcta");
            numCheck = +1;
        }

        return numCheck;

    }*/

   /* private boolean AreaCorrecta()
    {
        //Log.w(TAG, "Corrigiendo");


        if(( > 290) && (x1.centerX < 310 )) {
            Log.w(TAG, "check 1");
            return true;
        }
        /*if((x2.centerX > 590) && (x2.centerX > 610 ) && (x2.centerY > 190) && (x2.centerY < 210))
            return true;
        if((x3.centerX > 890) && (x3.centerX > 910 ) && (x3.centerY > 190) && (x3.centerY < 210))
            return true;
        else {
            Log.w(TAG, "check 0");
            return false;
        }
    }*/

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




}
