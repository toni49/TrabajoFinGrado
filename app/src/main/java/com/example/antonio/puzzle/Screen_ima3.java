package com.example.antonio.puzzle;

/**
 * Created by antonio on 8/11/16.
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
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;




public class Screen_ima3 extends View {

    private static final String TAG = "Screen_ima3";
    private Button botonIni;
    public Canvas canvas;
    // Activity de la clase Play
    private Activity newActivity = null;
    /**
     * Main bitmap
     */
    private Bitmap coche_rojo_sp = null, coche_rojo = null, coche_azul_sp = null, coche_azul = null, coche_amarillo = null, coche_amarillo_sp = null;
    private Bitmap home_Bitmap = null, next_Bitmap = null, save_Bitmap = null, speak_Bitmap = null;



    private BitArea x1, x2, x3, x4;
    private int valor = 1;
    private boolean flag_save = false;
    long endTime= 0, initialTime = 0, totalTime = 0;
    private long tiempo1 = 0, tiempo2 = 0;
    int check1 = 0, check2 = 0, check3 = 0;
    int fail = 0;
    VelocityTracker tracker = null;
    static float MaxVelocity_x = 0;
    static float MaxVelocity_y = 0;
    Mostrar_nivel mostrar = new Mostrar_nivel();
    Registro_datos registro = new Registro_datos();
    Level registros = new Level();

    AudioRecordTest speak = new AudioRecordTest();


    public Screen_ima3(Context context, Activity activity) {
        super(context);
        newActivity = activity;

        init(context);

    }

    public Screen_ima3(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }

    public Screen_ima3(Context context, AttributeSet attrs, int defStyleAttr) {
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

        next_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.check);
        home_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.home);
        speak_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.speaker);
        save_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.register);

        coche_azul_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.coche_azul_sp);
        coche_azul = BitmapFactory.decodeResource(context.getResources(), R.drawable.coche_azul);
        coche_rojo = BitmapFactory.decodeResource(context.getResources(), R.drawable.coche_rojo);
        coche_rojo_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.coche_rojo_sp);
        coche_amarillo = BitmapFactory.decodeResource(context.getResources(), R.drawable.coche_amarillo);
        coche_amarillo_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.coche_amarillo_sp);


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
        paintText.setColor(Color.BLACK);
        paintText.setStrokeWidth(7);
        String texto = "COLOQUE LAS FICHAS";
        canv.drawText(texto, 750, 900, paintText);

        //imagen boton de checkeo
        canv.drawBitmap(next_Bitmap, 1160, 20, null);
        canv.drawBitmap(save_Bitmap, 1060, 20, null);
        canv.drawBitmap(speak_Bitmap, 120, 20, null);
        canv.drawBitmap(home_Bitmap, 20, 20, null);

        //silueta de las imagenes
        canv.drawBitmap(coche_amarillo_sp, 50, 280, null);
        canv.drawBitmap(coche_azul_sp, 50, 500, null);
        canv.drawBitmap(coche_rojo_sp, 650, 280, null);


        // Posición inicial de figuras dinámicas

        x1 = obtainTouchedBit(730, 500);
        x2 = obtainTouchedBit(700, 530);
        x3 = obtainTouchedBit(700, 500);


        for (BitArea imagen : mBit) {
            if (imagen.num == 1)
                canv.drawBitmap(coche_rojo, imagen.leftX, imagen.leftY, null);
            else if (imagen.num == 2)
                canv.drawBitmap(coche_amarillo, imagen.leftX, imagen.leftY, null);
            else
                canv.drawBitmap(coche_azul, imagen.leftX, imagen.leftY, null);


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
                touchedBit = obtainTouchedBit(xTouch, yTouch);
                touchedBit.leftX = xTouch;
                touchedBit.leftY = yTouch;

                mBitPointer.put(event.getPointerId(0), touchedBit);


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

                    touchedBit = mBitPointer.get(pointerId);


                    if (null != touchedBit) {
                        touchedBit.leftX = xTouch;
                        touchedBit.leftY = yTouch;

                    }

                    if (touchedBit.num == 1) {
                        //comprobamos que el circulo pulsado se situa en la posicion correcta.
                        if ((touchedBit.leftX > 640) && (touchedBit.leftX < 660) && (touchedBit.leftY > 270) && (touchedBit.leftY < 290)) {
                            Log.w(TAG, "circulo 1");
                            check1 = 1;
                            touchedBit.leftX = 650;
                            touchedBit.leftY = 280;
                        }
                    }

                    if (touchedBit.num == 2) {

                        if ((touchedBit.leftX > 40) && (touchedBit.leftX < 60) && (touchedBit.leftY > 270) && (touchedBit.leftY < 290)) {
                            Log.w(TAG, "circulo 2");
                            check2 = 1;
                            touchedBit.leftX = 50;
                            touchedBit.leftY = 280;
                        }
                    }

                    if (touchedBit.num == 3) {

                        if ((touchedBit.leftX > 40) && (touchedBit.leftX < 60) && (touchedBit.leftY > 480) && (touchedBit.leftY < 510)) {
                            Log.w(TAG, "circulo 2");
                            check3 = 1;
                            touchedBit.leftX = 50;
                            touchedBit.leftY = 500;
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
                    String num1 = Integer.toString(check1);
                    String num2 = Integer.toString(check2);
                    String num3 = Integer.toString(check3);


                    Log.w(num1, "valor check1");
                    Log.w(num2, "valor check2");
                    Log.w(num3, "valor check3");



                    if ((check1 == 1) && (check2 == 1) && (check3 == 1)) {

                        Log.w(TAG, "funcionando");

                        tiempo2 = System.currentTimeMillis();
                        tiempo2 = tiempo2 - tiempo1;
                        registros.setTiempo(tiempo2);

                        mostrar.set_fallos(fail);
                        mostrar.set_nivel(3);
                        registros.setPuzzle(9);

                        Intent intent = new Intent(getContext(), Level.class);
                        newActivity.startActivity(intent);

                       // Screen_ima2 screen_ima2 = new Screen_ima2(getContext(), newActivity);
                       // newActivity.setContentView(screen_ima2);

                       // screen_ima2.destroyDrawingCache();
                       // Intent intent = new Intent(getContext(), Level.class);
                        /*Circles.clear();       //Las piezas se borran y se vuelven a dibujar en la posicion exacto, creando un efecto de colocación.
                        x1 = obtainTouchedSquare(1200, 200);
                        x2 = obtainTouchedSquare(200, 200);*/


                        //animation.start();
                        // playActivity.setContentView();
                    } else {
                        fail = fail + 1;  //aumentamos la variable fail en caso de no acertar puzzle.
                        String fallo = Integer.toString(fail);
                        Log.w(fallo, "numero de fallos");
                        check1 = 0;
                        check2 = 0;
                        check3 = 0;
                        mBit.clear();
                        x1 = obtainTouchedBit(700, 300);
                        x2 = obtainTouchedBit(700, 400);
                        x3 = obtainTouchedBit(700, 500);
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
                }

                else if ((xTouch > 110) && (xTouch < 180) && (yTouch > 1) && (yTouch < 80)) {
                    Log.w(TAG, "Audio Record");

                    speak.startPlaying();

                }

                else if ((xTouch > 1040) && (xTouch < 1120) && (yTouch > 1) && (yTouch < 80)) {
                    Log.w(TAG, "Guardar variables");
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

        return super.onTouchEvent(event) || handled;
    }

    /**
     * Clears all CircleArea - pointer id relations
     */
    private void clearBitPointer() {
        Log.w(TAG, "clear BitPointer");

        mBitPointer.clear();
    }

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
            if ((imagen.leftX < xTouch) && ((imagen.leftX + 500) > xTouch) && (imagen.leftY < yTouch) && ((imagen.leftY + 100) > yTouch)) {
                touched = imagen;
                break;
            }
        }

        return touched;
    }
    //////////////////////////////////////////////////////////////////////////////////////


}
