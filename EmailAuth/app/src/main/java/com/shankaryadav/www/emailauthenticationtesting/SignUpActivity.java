package com.shankaryadav.www.emailauthenticationtesting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText email,pass;
    Button signup;

    String emailStr = null;
    String passStr = null;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign_up);

        email = findViewById (R.id.email);
        pass = findViewById (R.id.password);
        signup = findViewById (R.id.signup);

        firebaseAuth = FirebaseAuth.getInstance ();

        signup.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                getAuthentication ();
            }
        });
    }

    public  void getAuthentication(){
        emailStr = email.getText ().toString ();
        passStr = pass.getText ().toString ();

        firebaseAuth.createUserWithEmailAndPassword (emailStr,passStr)
                .addOnCompleteListener (this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful ()){
                            Toast.makeText (SignUpActivity.this, "Registration successful", Toast.LENGTH_SHORT).show ();

                            Intent intent= new Intent (SignUpActivity.this,LoginActivity.class);
                            startActivity (intent);
                            finish ();
                        }else {
                            Toast.makeText (SignUpActivity.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show ();
                        }
                    }
                }).addOnFailureListener (this, new OnFailureListener () {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText (SignUpActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show ();
            }
        }).addOnCanceledListener (this, new OnCanceledListener () {
            @Override
            public void onCanceled() {
                Toast.makeText (SignUpActivity.this, "Authentication is cancelled", Toast.LENGTH_SHORT).show ();
            }
        });
    }
}
