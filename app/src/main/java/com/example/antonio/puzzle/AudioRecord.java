package com.example.antonio.puzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.media.MediaRecorder;
import android.media.MediaPlayer;

import java.io.IOException;


/**
 * Created by antonio on 11/6/16.
 */
public class AudioRecord extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "AudioRecord";
    private static String mFileName = null;

    ImageButton mRecordButton = null;
    ImageButton mPlayButton = null;
    ImageButton mStopButton= null;
    Button back;





    @Override
    public void onClick(View v) {
        final MediaPlayer click = MediaPlayer.create(this, R.raw.click);

        switch (v.getId()) {

            case R.id.button_record:
                click.start();
                onRecord(true);
                break;

            case R.id.button_play:
                click.start();
                onPlay(true);
                break;

            case R.id.button_stop:
                click.start();
                onRecord(false);
                break;

            case R.id.button_back:
                click.start();
                startActivity(new Intent(this, Opciones.class));
                break;

            default:
                break;

        }
    }

    //private RecordButton mRecordButton = null;
    private MediaRecorder mRecorder = null;

    //private PlayButton   mPlayButton = null;
    private MediaPlayer mPlayer = null;


    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }


    public void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }




    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {

        try {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }catch(NullPointerException e) {
            e.printStackTrace();
        }
    }



    public AudioRecord() {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);


        setContentView(R.layout.audio_record);

        mRecordButton=(ImageButton)findViewById(R.id.button_record);
        mPlayButton = (ImageButton) findViewById(R.id.button_play);
        mStopButton = (ImageButton) findViewById(R.id.button_stop);
        back = (Button) findViewById(R.id.button_back);

        mRecordButton.setOnClickListener(this);
        mPlayButton.setOnClickListener(this);
        mStopButton.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

}
