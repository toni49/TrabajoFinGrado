package com.example.antonio.puzzle;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.Paint;



/**
 * Created by antonio on 7/17/16.
 */
public class Painter {

    private Canvas canvas;
    private Paint paint;

    public void drawImage(Bitmap bitmap, int x, int y)
    {
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void drawString(String str, int x, int y)
    {
        canvas.drawText(str, x, y, paint);
    }

}

