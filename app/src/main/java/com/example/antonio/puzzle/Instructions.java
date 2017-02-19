package com.example.antonio.puzzle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by antonio on 2/18/17.
 */
public class Instructions extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "Instructions Activity";
    private Button volver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);

        Log.w(TAG, "instrucciones");

        volver = (Button) findViewById(R.id.buttonBack);

        volver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final MediaPlayer click = MediaPlayer.create(this, R.raw.click);


        switch (v.getId()) {
            case R.id.buttonBack: //iniciamos primer puzle
                click.start();
                startActivity(new Intent(this, Opciones.class));
                break;
        }
    }
}
