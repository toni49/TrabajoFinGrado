package com.example.antonio.puzzle;

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

/**
 * Created by antonio on 9/10/16.
 */

    public class Screen_1 extends View {

        private static final String TAG = "Screen_2";
        int valor = 1;



        // Activity de la clase Play
        private Activity newActivity = null;

        /** Main bitmap */
        private Bitmap next_Bitmap = null, pause_Bitmap = null;


        private Rect Rect1, Rect2;
        private SquareArea s1, s2, s3, s4, s5, s6;
        private float w, h;
        int check1 = 0, check2 = 0, check3 = 0, check4 = 0, check5 = 0, check6 = 0;
        int fail = 0;
        Mostrar_nivel mostrar = new Mostrar_nivel();

        public Screen_1(Context context, Activity activity) {
            super(context);
            newActivity = activity;

            init(context);

        }

        public Screen_1(Context context, AttributeSet attrs) {
            super(context, attrs);

            init(context);

        }

        public Screen_1(Context context, AttributeSet attrs, int defStyleAttr) {
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
        private Paint yellowPaint, redPaint, bluePaint, whitePaint, greenPaint, blackPaint;
        private Paint red_stroke, yellow_Stroke, blue_Stroke, green_Stroke, black_Stroke, white_Stroke;


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
            redPaint.setColor(Color.MAGENTA);
            redPaint.setStyle(Paint.Style.FILL);

            yellowPaint = new Paint();
            yellowPaint.setColor(Color.YELLOW);
            yellowPaint.setStyle(Paint.Style.FILL);

            whitePaint = new Paint();
            whitePaint.setColor(Color.WHITE);
            whitePaint.setStyle(Paint.Style.FILL);

            blackPaint = new Paint();
            blackPaint.setColor(Color.BLACK);
            blackPaint.setStyle(Paint.Style.FILL);

            red_stroke = new Paint();
            red_stroke.setStyle(Paint.Style.STROKE);
            red_stroke.setStrokeWidth(7);
            red_stroke.setColor(Color.MAGENTA);

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

            white_Stroke = new Paint();
            white_Stroke.setStyle(Paint.Style.STROKE);
            white_Stroke.setStrokeWidth(7);
            white_Stroke.setColor(Color.WHITE);

            black_Stroke = new Paint();
            black_Stroke.setStyle(Paint.Style.STROKE);
            black_Stroke.setStrokeWidth(7);
            black_Stroke.setColor(Color.BLACK);

        }

        @Override
        public void onDraw(final Canvas canv) {
            // background bitmap to cover all area

            setBackgroundResource(R.drawable.madera_1);

            canv.drawRect(200, 80, 350, 120, whitePaint);
            canv.drawRect(200, 80, 350, 120, red_stroke);

            canv.drawRect(150, 150, 400, 190, whitePaint);
            canv.drawRect(150, 150, 400, 190, red_stroke);

            canv.drawRect(100, 220, 450, 260, whitePaint);
            canv.drawRect(100, 220, 450, 260, red_stroke);

            canv.drawRect(800, 80, 950, 120, whitePaint);
            canv.drawRect(800, 80, 950, 120, black_Stroke);

            canv.drawRect(750, 150, 1000, 190, whitePaint);
            canv.drawRect(750, 150, 1000, 190, black_Stroke);

            canv.drawRect(700, 220, 1050, 260, whitePaint);
            canv.drawRect(700, 220, 1050, 260, black_Stroke);



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

            s1 = obtainTouchedSquare(300, 450);
            s2 = obtainTouchedSquare(300, 550);
            s3 = obtainTouchedSquare(300, 650);
            s4 = obtainTouchedSquare(700, 450);
            s5 = obtainTouchedSquare(700, 550);
            s6 = obtainTouchedSquare(700, 650);


            for (SquareArea square: mSquare) {

                if(square.num == 1)
                    canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, redPaint);
                else if(square.num == 2)
                    canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, redPaint);
                else if(square.num == 3)
                    canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, redPaint);
                else if(square.num == 4)
                    canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, blackPaint);
                else if(square.num == 5)
                    canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, blackPaint);
                else
                    canv.drawRect(square.leftX, square.leftY, square.leftX + square.Swidth, square.leftY + square.Sheight, blackPaint);


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

                        touchedSquare = mSquarePointer.get(pointerId);



                        if (null != touchedSquare) {

                            touchedSquare.leftX = xTouch;
                            touchedSquare.leftY = yTouch;
                        }

                        //comprobamos que el circulo pulsado se situa en la posicion correcta.

                        if (touchedSquare.num == 1) {

                            if ((touchedSquare.leftX > 180) && (touchedSquare.leftX < 220) && (touchedSquare.leftY > 60) && (touchedSquare.leftY < 100))
                                check1 = 1;


                        }

                        if (touchedSquare.num == 2) {

                            if ((touchedSquare.leftX > 130) && (touchedSquare.leftX < 170) && (touchedSquare.leftY > 130) && (touchedSquare.leftY < 170))
                                check2 = 1;

                        }

                        if (touchedSquare.num == 3) {

                            if ((touchedSquare.leftX > 80) && (touchedSquare.leftX < 120) && (touchedSquare.leftY > 200) && (touchedSquare.leftY < 240))
                                check3 = 1;

                        }

                        if (touchedSquare.num == 4) {

                            if ((touchedSquare.leftX > 780) && (touchedSquare.leftX < 820) && (touchedSquare.leftY > 60) && (touchedSquare.leftY < 100))
                                check4 = 1;

                        }

                        if (touchedSquare.num == 5) {

                            if ((touchedSquare.leftX > 730) && (touchedSquare.leftX < 770) && (touchedSquare.leftY > 130) && (touchedSquare.leftY < 170))
                                check5 = 1;

                        }

                        if (touchedSquare.num == 6) {

                            if ((touchedSquare.leftX > 680) && (touchedSquare.leftX < 720) && (touchedSquare.leftY > 200) && (touchedSquare.leftY < 240))
                                check6 = 1;

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
                        if ((check1 == 1) && (check2 == 1) && (check3 == 1) && (check4 == 1) && (check5 == 1) && (check6 == 1)) {
                            check1 = 0;
                            check2 = 0;
                            check3 = 0;
                            check4 = 0;
                            check5 = 0;
                            check6 = 0;

                            Log.w(TAG, "funcionando");
                            mostrar.set_fallos(fail);
                            //mostrar.set_nivel(2);

                            Screen_2 screen_2 = new Screen_2(getContext(), newActivity);
                            newActivity.setContentView(screen_2);

                            //Intent intent = new Intent(getContext(), Level.class);
                            //newActivity.startActivity(intent);

                        }
                        else {
                            fail = fail + 1;  //aumentamos la variable fail en caso de no acertar puzzle.
                            String fallo = Integer.toString(fail);
                            Log.w(fallo, "numero de fallos");
                            mSquare.clear();
                            s1 = obtainTouchedSquare(300, 450);
                            s2 = obtainTouchedSquare(300, 550);
                            s3 = obtainTouchedSquare(300, 650);
                            s4 = obtainTouchedSquare(700, 450);
                            s5 = obtainTouchedSquare(700, 550);
                            s6 = obtainTouchedSquare(700, 650);

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

        //////////////////////////////////////////////////////////////////////////////////////

        private SquareArea obtainTouchedSquare(final int xTouch, final int yTouch) {
            SquareArea touchedSquare = getTouchedSquare(xTouch, yTouch);


            if (null == touchedSquare) {

                switch(valor)
                {
                    case 1:
                        touchedSquare = new SquareArea(150, 40, xTouch, yTouch, 1);
                        valor = 2;
                        break;
                    case 2:
                        touchedSquare = new SquareArea(250, 40, xTouch, yTouch, 2);
                        valor = 3;
                        break;
                    case 3:
                        touchedSquare = new SquareArea(350, 40, xTouch, yTouch, 3);
                        valor = 4;
                        break;
                    case 4:
                        touchedSquare = new SquareArea(150, 40, xTouch, yTouch, 4);
                        valor = 5;
                        break;
                    case 5:
                        touchedSquare = new SquareArea(250, 40, xTouch, yTouch, 5);
                        valor = 6;
                        break;
                    case 6:
                        touchedSquare = new SquareArea(350, 40, xTouch, yTouch, 6);
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


