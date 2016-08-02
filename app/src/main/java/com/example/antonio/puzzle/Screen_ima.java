package com.example.antonio.puzzle;

/**
 * Created by antonio on 7/30/16.
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
import android.view.View;
import android.widget.Button;

import java.util.HashSet;




public class Screen_ima extends View {

    private static final String TAG = "Screen_4";
    private Button botonIni;
    public Canvas canvas;
    // Activity de la clase Play
    private Activity newActivity = null;
    public Ini_screen finish;
    /**
     * Main bitmap
     */
    private Bitmap ping_1 = null, ping_sp = null, ping_2 = null, ping2_sp = null, ping_3 = null, ping3_sp = null;
    private Bitmap pause_Bitmap = null, next_Bitmap = null;
    private Bitmap Bitma = null;


    private Rect Rect1, Rect2;
    private BitArea x1, x2, x3, x4;
    private float w, h;
    private int valor = 1;
    int check1 = 0, check2 = 0, check3 = 0, check4 = 0;
    MainActivity main = new MainActivity();


    public Screen_ima(Context context, Activity activity) {
        super(context);
        newActivity = activity;

        init(context);

    }

    public Screen_ima(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }

    public Screen_ima(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);

    }


    // Guardamos atributos de la clase circulo.
    public static class BitArea {
        int leftX;
        int leftY;
        int num;

        BitArea(int centerX, int centerY, int num) {
            this.leftX = centerX;
            this.leftY = centerY;
            this.num = num;
        }
    }

    // Guardamos atributos de la clase cuadrado.
    public static class SquareArea {
        int Swidth;
        int Sheight;
        int leftY;
        int leftX;

        SquareArea(int Swidth, int Sheight, int leftX, int leftY) {
            this.Swidth = Swidth;
            this.Sheight = Sheight;
            this.leftX = leftX;
            this.leftY = leftY;

        }
    }


    private Paint mCirclePaint;
    private Paint Circle_stroke;
    private Paint mSquarePaint;
    private Paint Square_stroke;
    private Paint mRectPaint;
    private Paint Rect_stroke;
    private Paint mFondoPaint;

    private static final int SHAPES_LIMIT = 4;

    private HashSet<BitArea> mBit = new HashSet<BitArea>(SHAPES_LIMIT);
    private SparseArray<BitArea> mBitPointer = new SparseArray<BitArea>(SHAPES_LIMIT);

    private HashSet<SquareArea> mSquare = new HashSet<SquareArea>(SHAPES_LIMIT);
    private SparseArray<SquareArea> mSquarePointer = new SparseArray<SquareArea>(SHAPES_LIMIT);


    private void init(final Context context) {

        next_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.next);
        pause_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pause);
        ping_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping_1);
        ping_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping_sp);
        ping_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping_2);
        ping2_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping2_sp);
        ping_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping_3);
        ping3_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping3_sp);







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

        Square_stroke = new Paint();
        Square_stroke.setStyle(Paint.Style.STROKE);
        Square_stroke.setStrokeWidth(5);
        Square_stroke.setColor(Color.RED);
        setBackgroundResource(R.drawable.fondo_hielo);

        //Cirulos fijos
        // canv.drawCircle(200, 200, 180, mFondoPaint);
        // canv.drawCircle(200, 200, 180, Circle_stroke);

        //cuadrados fijos.
        /*canv.drawRect(100, 100, 250, 250, mFondoPaint);
        canv.drawRect(100, 100, 250, 250, Square_stroke);
        canv.drawRect(400, 100, 600, 300, mFondoPaint);
        canv.drawRect(400, 100, 600, 300, Square_stroke);
        canv.drawRect(800, 100, 1050, 350, mFondoPaint);
        canv.drawRect(800, 100, 1050, 350, Square_stroke);
        canv.drawRect(1300, 100, 1600, 400, mFondoPaint);
        canv.drawRect(1300, 100, 1600, 400, Square_stroke);*/


      /*  Rect_stroke = new Paint();
        Rect_stroke.setStyle(Paint.Style.STROKE);
        Rect_stroke.setStrokeWidth(5);
        Rect_stroke.setColor(Color.RED);*/

        // canv.drawCircle(1200, 200, 120, mFondoPaint);
        // canv.drawCircle(1200, 200, 120, Rect_stroke);


        //Texto indicativo.
        Paint paintText = new Paint();
        paintText.setTextSize(30);
        paintText.setColor(Color.BLACK);
        paintText.setStrokeWidth(5);
        String texto = "COLOQUE LAS FICHAS";
        canv.drawText(texto, 750, 900, paintText);

        //imagen boton de checkeo
        canv.drawBitmap(next_Bitmap, 1740, 790, null);
        canv.drawBitmap(pause_Bitmap, 70, 790, null);

        //silueta de las imagenes
        canv.drawBitmap(ping_sp, 200, 200, null);
        canv.drawBitmap(ping2_sp, 500, 650, null);
        canv.drawBitmap(ping3_sp, 950, 200, null);



        // Posición inicial de figuras dinámicas

        x1 = obtainTouchedBit(1500, 100);
        x2 = obtainTouchedBit(1500, 400);
        x3 = obtainTouchedBit(1400, 650);




        for (BitArea imagen : mBit) {
            if (imagen.num == 1)
                canv.drawBitmap(ping_1, imagen.leftX, imagen.leftY, mRectPaint);
            else if (imagen.num == 2)
                canv.drawBitmap(ping_2, imagen.leftX, imagen.leftY, mRectPaint);
            else
                canv.drawBitmap(ping_3, imagen.leftX, imagen.leftY, mRectPaint);


        }

    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        boolean handled = false;

        //CircleArea touchedCircle;
        BitArea touchedBit;

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
                touchedBit = obtainTouchedBit(xTouch, yTouch);
                touchedBit.leftX = xTouch;
                touchedBit.leftY = yTouch;

                mBitPointer.put(event.getPointerId(0), touchedBit);


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

                    touchedBit = mBitPointer.get(pointerId);


                    if (null != touchedBit) {
                        touchedBit.leftX = xTouch;
                        touchedBit.leftY = yTouch;

                    }

                    if(touchedBit.num == 1) {
                        //comprobamos que el circulo pulsado se situa en la posicion correcta.
                        if ((touchedBit.leftX > 190) && (touchedBit.leftX < 210) && (touchedBit.leftY > 190) && (touchedBit.leftY < 210)) {
                            Log.w(TAG, "circulo 1");
                            check1 = 1;

                        } else
                            check1 = 0;
                    }

                    if(touchedBit.num == 2) {

                        if ((touchedBit.leftX > 490) && (touchedBit.leftX < 510) && (touchedBit.leftY > 640) && (touchedBit.leftY < 660)) {
                            Log.w(TAG, "circulo 2");

                            check2 = 1;

                        } else
                            check2 = 0;
                    }

                    if(touchedBit.num == 3) {

                        if ((touchedBit.leftX > 940) && (touchedBit.leftX < 960) && (touchedBit.leftY > 190) && (touchedBit.leftY < 210)) {
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
                    String num1 = Integer.toString(check1);
                    String num2 = Integer.toString(check2);
                    String num3 = Integer.toString(check3);


                    Log.w(num1, "valor check1");
                    Log.w(num2, "valor check2");
                    Log.w(num3, "valor check3");

                    if ((check1 == 1) && (check2 == 1) && (check3 == 1)){
                        check1 = 0;
                        check2 = 0;
                        check3 = 0;
                        Log.w(TAG, "funcionando");
                        /*mCircles.clear();       //Las piezas se borran y se vuelven a dibujar en la posicion exacto, creando un efecto de colocación.
                        x1 = obtainTouchedSquare(1200, 200);
                        x2 = obtainTouchedSquare(200, 200);*/


                        //animation.start();
                        // playActivity.setContentView();
                    }
                } else if ((xTouch > 40) && (xTouch < 200) && (yTouch > 760) && (yTouch < 940)) {
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
     * @return obtained {@link BitArea}
     */
    private BitArea obtainTouchedBit(final int xTouch, final int yTouch) {
       BitArea touchedBit = getTouchedBit(xTouch, yTouch);


        if (null == touchedBit) {

            switch (valor) {

                case 1:
                    touchedBit = new BitArea(xTouch, yTouch, 1);
                    valor = 2;
                    break;

                case 2:
                    touchedBit = new BitArea(xTouch, yTouch, 2);
                    valor = 3;
                    break;

                case 3:
                    touchedBit = new BitArea(xTouch, yTouch, 3);
                    valor = 1;
                    break;


            }

            if (mBit.size() < 3) {
                Log.w(TAG, "Added imagen " + touchedBit);
                mBit.add(touchedBit);
            }
        }

        return touchedBit;
    }

    /**
     * Determines touched circle
     *
     * @param xTouch int x touch coordinate
     * @param yTouch int y touch coordinate
     * @return {@link BitArea} touched circle or null if no circle has been touched
     */
    private BitArea getTouchedBit(final int xTouch, final int yTouch) {
        BitArea touched = null;

        for (BitArea imagen : mBit) {
            if ((imagen.leftX  < xTouch) && ((imagen.leftX + 250) > xTouch) && (imagen.leftY < yTouch) && ((imagen.leftY + 300) > yTouch))  {
                touched = imagen;
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

            switch (valor) {

                case 1:
                    touchedSquare = new SquareArea(150, 150, xTouch, yTouch);
                    valor = 2;
                    break;

                case 2:
                    touchedSquare = new SquareArea(200, 200, xTouch, yTouch);
                    valor = 3;
                    break;

                case 3:
                    touchedSquare = new SquareArea(250, 250, xTouch, yTouch);
                    valor = 4;
                    break;

                case 4:
                    touchedSquare = new SquareArea(300, 300, xTouch, yTouch);
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
            if ((((square.leftX + square.Swidth) > xTouch) && ((square.leftX) < xTouch)) && (((square.leftY + square.Sheight) > yTouch) && ((square.leftY) < yTouch))) {
                Log.w(TAG, "cuadrado tocado");

                touched = square;
                break;
            }
        }

        return touched;
    }
}