package com.shankaryadav.www.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    ImageView imageView;

    @Override
    protected void onStart() {
        super.onStart ();

        FirebaseUser  user = mAuth.getCurrentUser ();

        if (user != null){
            String photurl = user.getPhotoUrl ().toString ();
            photurl = photurl + "?height=500";

            Glide.with(getApplicationContext ()).load(photurl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance ();
        imageView = findViewById (R.id.imageView);



    }





}
