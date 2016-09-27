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
import android.view.View;
import android.widget.Button;

import java.util.HashSet;




public class Screen_ima2 extends View {

    private static final String TAG = "Screen_ima2";
    private Button botonIni;
    public Canvas canvas;
    // Activity de la clase Play
    private Activity newActivity = null;
    /**
     * Main bitmap
     */
    private Bitmap leon = null, leon_sp = null, girafa = null, girafa_sp = null, cocodrilo = null, cocodrilo_sp = null, elefante = null, elefante_sp = null;
    private Bitmap pause_Bitmap = null, next_Bitmap = null;
    private Bitmap Bitma = null;


    private Rect Rect1, Rect2;
    private BitArea x1, x2, x3, x4;
    private float w, h;
    int fail = 0;
    Mostrar_nivel mostrar = new Mostrar_nivel();
    private int valor = 1;
    int check1 = 0, check2 = 0, check3 = 0, check4 = 0;
    MainActivity main = new MainActivity();


    public Screen_ima2(Context context, Activity activity) {
        super(context);
        newActivity = activity;

        init(context);

    }

    public Screen_ima2(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }

    public Screen_ima2(Context context, AttributeSet attrs, int defStyleAttr) {
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
        leon = BitmapFactory.decodeResource(context.getResources(), R.drawable.leon);
        leon_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.leon_sp);
        elefante = BitmapFactory.decodeResource(context.getResources(), R.drawable.elefante);
        elefante_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.elefante_sp);
        girafa = BitmapFactory.decodeResource(context.getResources(), R.drawable.girafa);
        girafa_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.girafa_sp);
        cocodrilo = BitmapFactory.decodeResource(context.getResources(), R.drawable.cocodrilo);
        cocodrilo_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cocodrilo_sp);


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
        setBackgroundResource(R.drawable.madera_1);



        //Texto indicativo.
        Paint paintText = new Paint();
        paintText.setTextSize(40);
        paintText.setColor(Color.RED);
        paintText.setStrokeWidth(6);
        String texto = "COLOQUE LAS FICHAS";
        canv.drawText(texto, 750, 1000, paintText);

        //imagen boton de checkeo
        canv.drawBitmap(next_Bitmap, 1170, 600, null);
        canv.drawBitmap(pause_Bitmap, 70, 600, null);

        //silueta de las imagenes
        canv.drawBitmap(girafa_sp, 70, 60, null);
        canv.drawBitmap(elefante_sp, 350, 120, null);
        canv.drawBitmap(cocodrilo_sp, 680, 140, null);
        canv.drawBitmap(leon_sp, 1000, 120, null);



        // Posición inicial de figuras dinámicas

        x1 = obtainTouchedBit(200, 500);
        x2 = obtainTouchedBit(900, 350);
        x3 = obtainTouchedBit(450, 500);
        x4 = obtainTouchedBit(700, 360);


        for (BitArea imagen : mBit) {
            if (imagen.num == 1)
                canv.drawBitmap(leon, imagen.leftX, imagen.leftY, mRectPaint);
            else if (imagen.num == 2)
                canv.drawBitmap(girafa, imagen.leftX, imagen.leftY, mRectPaint);
            else if (imagen.num == 3)
                canv.drawBitmap(elefante, imagen.leftX, imagen.leftY, mRectPaint);
            else
                canv.drawBitmap(cocodrilo, imagen.leftX, imagen.leftY, mRectPaint);


        }

    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        boolean handled = false;
        Canvas canv = null;

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

                    if (touchedBit.num == 1) {
                        //comprobamos que el circulo pulsado se situa en la posicion correcta.
                        if ((touchedBit.leftX > 990) && (touchedBit.leftX < 1010) && (touchedBit.leftY > 110) && (touchedBit.leftY < 130)) {
                            Log.w(TAG, "circulo 1");
                            check1 = 1;
                            touchedBit.leftX = 1000;
                            touchedBit.leftY = 120;

                        }
                    }

                    if (touchedBit.num == 2) {

                        if ((touchedBit.leftX > 60) && (touchedBit.leftX < 80) && (touchedBit.leftY > 50) && (touchedBit.leftY < 70)) {
                            Log.w(TAG, "circulo 2");
                            check2 = 1;
                            touchedBit.leftX = 70;
                            touchedBit.leftY = 60;

                        }
                    }

                    if (touchedBit.num == 3) {

                        if ((touchedBit.leftX > 340) && (touchedBit.leftX < 370) && (touchedBit.leftY > 110) && (touchedBit.leftY < 130)) {
                            Log.w(TAG, "circulo 3");
                            check3 = 1;
                            touchedBit.leftX = 350;
                            touchedBit.leftY = 120;

                        }
                    }

                    if (touchedBit.num == 4) {

                        if ((touchedBit.leftX > 670) && (touchedBit.leftX < 690) && (touchedBit.leftY > 130) && (touchedBit.leftY < 150)) {
                            Log.w(TAG, "circulo 4");
                            check4 = 1;
                            touchedBit.leftX = 680;
                            touchedBit.leftY = 140;

                        }
                    }


                }
                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_UP:
                xTouch = (int) event.getX(0);
                yTouch = (int) event.getY(0);

                if ((xTouch > 1100) && (xTouch < 1240) && (yTouch > 540) && (yTouch < 660)) {
                    Log.w(TAG, "PULSADO NEXT");
                    String num1 = Integer.toString(check1);
                    String num2 = Integer.toString(check2);
                    String num3 = Integer.toString(check3);


                    Log.w(num1, "valor check1");
                    Log.w(num2, "valor check2");
                    Log.w(num3, "valor check3");


                    if ((check1 == 1) && (check2 == 1) && (check3 == 1) && (check4 == 1)) {

                        Log.w(TAG, "funcionando");
                        mostrar.set_fallos(fail);
                        mostrar.set_nivel(3);
                        /*mCircles.clear();       //Las piezas se borran y se vuelven a dibujar en la posicion exacto, creando un efecto de colocación.
                        x1 = obtainTouchedSquare(1200, 200);
                        x2 = obtainTouchedSquare(200, 200);*/

                        Screen_ima screen_ima = new Screen_ima(getContext(), newActivity);
                        newActivity.setContentView(screen_ima);


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
                        mBit.clear();
                        x1 = obtainTouchedBit(200, 500);
                        x2 = obtainTouchedBit(900, 350);
                        x3 = obtainTouchedBit(450, 500);
                        x4 = obtainTouchedBit(700, 360);

                        invalidate();
                    }

                } else if ((xTouch > 10) && (xTouch < 130) && (yTouch > 540) && (yTouch < 660)) {
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
                    valor = 4;
                    break;

                case 4:
                    touchedBit = new BitArea(xTouch, yTouch, 4);
                    valor = 1;
                    break;


            }

            if (mBit.size() < 4) {
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
            if ((imagen.leftX < xTouch) && ((imagen.leftX + 250) > xTouch) && (imagen.leftY < yTouch) && ((imagen.leftY + 300) > yTouch)) {
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
