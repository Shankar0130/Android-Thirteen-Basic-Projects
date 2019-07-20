package com.shankaryadav.www.truthdaregameusinganimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

ImageButton imageButton;

    Random random;
    int last = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        imageButton = findViewById (R.id.imageButton2);



        imageButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                spin ();
            }
        });
    }

    public void spin(){
        random = new Random ();
        int angle = random.nextInt (3600);

        int h = imageButton.getHeight ()/2;
        int w = imageButton.getWidth ()/2;

        Animation animation = new RotateAnimation (last,angle,w,h);

        animation.setDuration (2000);

        animation.setFillAfter (true);

        animation.setAnimationListener (new Animation.AnimationListener () {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        last = angle;
         imageButton.startAnimation (animation);
    }
}
