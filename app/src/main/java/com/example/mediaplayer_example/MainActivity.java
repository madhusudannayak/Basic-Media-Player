package com.example.mediaplayer_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView play,pause,stop;
    SeekBar positionbar,volumebar;
    MediaPlayer mediaPlayer;
    int totaltime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        play=findViewById(R.id.play);
        pause=findViewById(R.id.pause);
        stop=findViewById(R.id.stop);

        mediaPlayer=MediaPlayer.create(this,R.raw.audio);
        totaltime =mediaPlayer.getDuration();

       positionbar=findViewById(R.id.positionbar);
       positionbar.setMax(totaltime);


       positionbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){
                mediaPlayer.seekTo(progress);
                positionbar.setProgress(progress);


            }


           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });
       new Timer().scheduleAtFixedRate(new TimerTask() {
           @Override
           public void run() {
               positionbar.setProgress(mediaPlayer.getCurrentPosition());
           }
       },0,10);



        AudioManager audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int pro=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        volumebar=findViewById(R.id.volumebar);
        volumebar.setMax(max);
        volumebar.setProgress(pro);

        volumebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

    }


    public void play(){
        mediaPlayer.start();
    }
    public void pause(){
        mediaPlayer.pause();
    }
    public void stop(){
        mediaPlayer.stop();
    }



}