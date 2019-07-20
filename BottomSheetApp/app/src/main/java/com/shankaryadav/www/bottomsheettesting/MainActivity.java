package com.shankaryadav.www.bottomsheettesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        textView = findViewById (R.id.getBottomSheet);

        textView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                ExampleBottomSheets bs = new ExampleBottomSheets ();

                bs.show (getSupportFragmentManager (),"abcdefgh");

            }
        });
    }
}
