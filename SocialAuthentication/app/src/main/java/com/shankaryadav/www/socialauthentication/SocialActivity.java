package com.shankaryadav.www.socialauthentication;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SocialActivity extends AppCompatActivity {

    Button mobile,face;

    FirebaseAuth firebaseAuth;

    GoogleSignInClient googleSignInClient;

    SignInButton signInButton;
    private int RC_SIGN_IN = 234;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null){
            Toast.makeText (this, "you are already sign in", Toast.LENGTH_SHORT).show ();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_social);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        mobile = findViewById (R.id.mobile);
        face = findViewById (R.id.facebook);


        firebaseAuth = FirebaseAuth.getInstance ();

        mobile.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (SocialActivity.this,MobileActivity.class);
                startActivity (intent);
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();



        googleSignInClient = GoogleSignIn.getClient (this,gso);

       signInButton.setOnClickListener (new View.OnClickListener () {
           @Override
           public void onClick(View v) {
               signin();
           }
       });

    }

    public void signin(){

        Intent signInIntent = googleSignInClient.getSignInIntent ();

        startActivityForResult (signInIntent,RC_SIGN_IN);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
      //  Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithCredential:success");
                            //FirebaseUser user = firebaseAuth.getCurrentUser();
                            //updateUI(user);

                            Toast.makeText (SocialActivity.this, "You are logged in with google", Toast.LENGTH_SHORT).show ();

                        } else {
                            // If sign in fails, display a message to the user.
                           // Log.w(TAG, "signInWithCredential:failure", task.getException());
                           // Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                          //  updateUI(null);

                            Toast.makeText (SocialActivity.this, "google Authentication failed", Toast.LENGTH_SHORT).show ();
                        }

                        // ...
                    }
                });
    }


}
