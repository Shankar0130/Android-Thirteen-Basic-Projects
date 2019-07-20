package com.shankaryadav.www.emailauthenticationtesting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email,pass;
    Button signup,login,forgotpassword;

    FirebaseAuth firebaseAuth;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null){
            Intent intent = new Intent (LoginActivity.this,MainActivity.class);
            startActivity (intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance ();

        email = findViewById (R.id.email);
        pass = findViewById (R.id.password);
        login = findViewById (R.id.login);
        signup = findViewById (R.id.sign_up);
        forgotpassword = findViewById (R.id.forgPass);


        login.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {


                String emailStr = email.getText ().toString ();
                String passStr = pass.getText ().toString ();


              firebaseAuth.signInWithEmailAndPassword (emailStr,passStr)
                      .addOnCompleteListener (LoginActivity.this, new OnCompleteListener<AuthResult> () {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful ()){
                                  Toast.makeText(LoginActivity.this, "Authentication Successful.",
                                          Toast.LENGTH_SHORT).show();

                                  Intent intent = new Intent (LoginActivity.this,MainActivity.class);
                                  startActivity (intent);
                              }else{
                                  Toast.makeText(LoginActivity.this, "Authentication failed.",
                                          Toast.LENGTH_SHORT).show();
                              }
                          }
                      });


            }
        });


        signup.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this,SignUpActivity.class);
                startActivity (intent);
            }
        });


        forgotpassword.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (LoginActivity.this,ForgotPasswordActivity.class);
                startActivity (intent);
            }
        });

    }


}
