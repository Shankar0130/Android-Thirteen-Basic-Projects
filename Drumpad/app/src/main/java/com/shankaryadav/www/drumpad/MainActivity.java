package com.shankaryadav.www.drumpad;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8;
    SoundPool soundPool;
    int[] sound = new int[8];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);


        b1 = findViewById (R.id.one);
        b2 = findViewById (R.id.two);
        b3 = findViewById (R.id.three);
        b4 = findViewById (R.id.four);
        b5 = findViewById (R.id.five);
        b6 = findViewById (R.id.six);
        b7 = findViewById (R.id.seven);
        b8 = findViewById (R.id.eight);

        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();


        sound[0] = soundPool.load (getApplicationContext (), R.raw.one, 1);
        sound[1] = soundPool.load (getApplicationContext (), R.raw.two, 1);
        sound[2] = soundPool.load (getApplicationContext (), R.raw.three, 1);
        sound[3] = soundPool.load (getApplicationContext (), R.raw.four, 1);
        sound[4] = soundPool.load (getApplicationContext (), R.raw.fv, 1);
        sound[5] = soundPool.load (getApplicationContext (), R.raw.sixth, 1);
        sound[6] = soundPool.load (getApplicationContext (), R.raw.seventh, 1);
        sound[7] = soundPool.load (getApplicationContext (), R.raw.eighth, 1);






soundPool.setOnLoadCompleteListener (new SoundPool.OnLoadCompleteListener () {
    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        Toast.makeText (MainActivity.this, "All file is loaded", Toast.LENGTH_SHORT).show ();
    }
});


        b1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                soundPool.play (sound[0],1,1,0,0,1f);
            }
        });

        b2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                soundPool.play (sound[1],1,1,0,0,1f);
            }
        });

        b3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                soundPool.play (sound[2],1,1,0,0,1f);
            }
        });

        b4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                soundPool.play (sound[3],1,1,0,0,1f);
            }
        });

        b5.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                soundPool.play (sound[4],1,1,0,0,1f);
            }
        });

        b6.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                soundPool.play (sound[5],1,1,0,0,1f);
            }
        });

        b7.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                soundPool.play (sound[6],1,1,0,0,1f);
            }
        });

        b8.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                soundPool.play (sound[7],1,1,0,0,1f);
            }
        });


    }
}
