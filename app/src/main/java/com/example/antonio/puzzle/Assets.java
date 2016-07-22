package com.example.antonio.puzzle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by antonio on 7/17/16.
 */
public class Assets {

    public static Bitmap rect_s, rect1;


    private static Bitmap loadBitmap(String filename)
    {
        InputStream inputStream = null;
        try
        {
            inputStream = MainActivity.assets.open(filename);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    public void load()
    {
        rect1 = loadBitmap("ic_launcher.png");
        rect_s = loadBitmap("");
    }
}
