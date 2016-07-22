package com.example.antonio.puzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


/**
 * Created by antonio on 7/17/16.
 */


    public class Painter extends View {

        private Rect rectangulo, rectangulo2;
        private Paint paint;
        Paint red_paintbrush_fill;
        Paint red_paintbrush_stroke;
        Path square;
        Canvas canvas;





        public Painter(Context context) {   //Superficie con todas las imagenes definidas
            super(context);
            setFocusableInTouchMode(true);
        }



        @Override
        public void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);

            int x = getWidth();
            int y = getHeight();
            int sideLengthx = 500;
            int sideLengthy = 300;
            Paint paint;

            canvas.drawColor(Color.argb(255, 255, 255, 255));

            // prepPaintBrush();

            //rectangulo = new Rect(x, y, sideLengthx, sideLengthy);
            paint = new Paint();
            paint.setColor(Color.BLUE);


            canvas.drawRect(getLeft()+getWidth()/6, getTop()+getHeight()/10, 200, 120, paint);



            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            paint.setColor(Color.BLACK);
            canvas.drawCircle(500, 200, 100,  paint);

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            canvas.drawCircle(150, 200, 100,  paint);



            //drawSquare(100, 100, 200, 120);

           // paint.setColor(Color.BLACK);
           // canvas.drawRect(500, 200, 100, 50, paint);

            //rectangulo2 = new Rect(x+100, y+250, 500, 300);
            //paint2 = new Paint();
            //paint2.setColor(Color.RED);
            //canvas.drawColor(Color.BLACK);
            //canvas.drawRect(rectangulo2, paint2);

        }

    public void prepPaintBrush()
    {
        red_paintbrush_fill = new Paint();
        red_paintbrush_fill.setColor(Color.RED);
        red_paintbrush_fill.setStyle(Paint.Style.FILL);

        red_paintbrush_stroke = new Paint();
        red_paintbrush_stroke.setColor(Color.RED);
        red_paintbrush_stroke.setStyle(Paint.Style.FILL);
    }

    public void drawSquare(int x1, int y1, int x2, int y2)
    {
        square = new Path();
        square.moveTo(x1, y1);
        square.lineTo(x2, y1);
        square.moveTo(x2, y1);
        square.lineTo(x2, y2);
        square.moveTo(x2, y2);
        square.lineTo(x1, y2);
        square.moveTo(x1, y2);
        square.lineTo(x1, y1);

        this.canvas.drawPath(square, red_paintbrush_stroke);
    }


}



