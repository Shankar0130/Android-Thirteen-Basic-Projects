package com.shankaryadav.www.emailauthenticationtesting;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText email;
    Button sendEmailBtn;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance ();

        email = findViewById (R.id.email);
        sendEmailBtn = findViewById (R.id.sendEmailButton);

        sendEmailBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText ().toString ();

                firebaseAuth.sendPasswordResetEmail (emailStr)
                        .addOnCompleteListener (new OnCompleteListener<Void> () {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful ()){
                                    sendEmailBtn.setText ("check you email");
                                    Toast.makeText (ForgotPasswordActivity.this, "password sent successfully", Toast.LENGTH_SHORT).show ();
                                }else{
                                    Toast.makeText (ForgotPasswordActivity.this, "password not sent successfully", Toast.LENGTH_SHORT).show ();
                                }

                            }
                        });

            }
        });
    }
}
