package com.shankaryadav.www.runtimepermission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        b1 = findViewById (R.id.button_one);
        b2 = findViewById (R.id.button_two);

        b1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){

                    if ((ContextCompat.checkSelfPermission (getApplicationContext (), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission (getApplicationContext (),Manifest.permission.READ_CONTACTS))!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions (MainActivity.this,new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS},1);
                    }
                }

            }
        });

        b2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
