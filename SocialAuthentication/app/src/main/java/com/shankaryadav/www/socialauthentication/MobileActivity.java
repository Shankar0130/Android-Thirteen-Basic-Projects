package com.shankaryadav.www.socialauthentication;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MobileActivity extends AppCompatActivity {

    EditText pn,otp;
    Button send;

    FirebaseAuth firebaseAuth;

    String pnoneString = "";

    String verId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_mobile);

        pn = findViewById (R.id.phone_number);
        otp = findViewById (R.id.otp);
        send = findViewById (R.id.send);

        firebaseAuth =  FirebaseAuth.getInstance ();
    }

    public void send(View view) {
        pnoneString = pn.getText ().toString ().trim ();

        if ((pnoneString.length ()<10) || TextUtils.isEmpty (pnoneString)){
            pn.setError ("valid number is required");
            pn.requestFocus ();
            return;
        }
        final String mobileNumber = "+91" + pnoneString;
        sendVerificationCode(mobileNumber);
    }

    public void sendVerificationCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
      mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks () {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode ();

            if (code != null){
                otp.setText (code);
                verify(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {


        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent (s, forceResendingToken);

            verId = s;
        }
    };

    public void verify(String otpcode){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential (verId,otpcode);
        signInWithCredential(credential);
    }

    public void signInWithCredential(PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential (credential)
                .addOnCompleteListener (this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful ()) {
                            Toast.makeText (MobileActivity.this, "you are successfully registered", Toast.LENGTH_SHORT).show ();
                            Intent intent = new Intent (MobileActivity.this, MainActivity.class);
                            startActivity (intent);
                        }
                        else{
                            Toast.makeText (MobileActivity.this, "you are not successfully registered", Toast.LENGTH_SHORT).show ();
                        }
                    }
                });
    }
}
