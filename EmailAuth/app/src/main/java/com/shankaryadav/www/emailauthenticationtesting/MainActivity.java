package com.shankaryadav.www.emailauthenticationtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        textView = findViewById (R.id.textview);
        button = findViewById (R.id.logout);

        FirebaseUser currentUser = FirebaseAuth.getInstance ().getCurrentUser ();
        if (currentUser != null){
            textView.setText (currentUser.getEmail ().toString ());
        }


    }
}
