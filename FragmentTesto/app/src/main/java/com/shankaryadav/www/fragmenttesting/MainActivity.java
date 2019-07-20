package com.shankaryadav.www.fragmenttesting;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener () {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId ()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();

                    break;
                case R.id.navigation_search:
                     fragment = new SearchFragment ();
                    break;

                case R.id.navigation_add:
                    fragment = new AddFragment ();

                   break;

                case R.id.navigation_like:
                    fragment = new LikeFragment ();

                    break;

                case R.id.navigation_profile:
                    fragment = new ProfileFragment ();

                    break;

            }
            return loadfrag (fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        BottomNavigationView navView = findViewById (R.id.nav_view);




        navView.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener);

        loadfrag (new HomeFragment ());
    }

    public boolean loadfrag(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager ().beginTransaction ().replace (R.id.framelayout,fragment).commit ();

            return  true;
        }
        return  false;
    }

}
