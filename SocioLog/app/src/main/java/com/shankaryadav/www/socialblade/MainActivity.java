package com.shankaryadav.www.socialblade;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    LinearLayout fb,g,li,wh,yt,tw,ins,bi,ntfl,red,qora,medium,pinterest,wikipedia,twitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        fb = findViewById (R.id.facebook);
        g = findViewById (R.id.google);
        li = findViewById (R.id.linkdin);
        wh = findViewById (R.id.whatsapp);
        yt = findViewById (R.id.youtube);
        tw = findViewById (R.id.twitter);
        ins = findViewById (R.id.insta);
        bi = findViewById (R.id.bing);
        ntfl = findViewById (R.id.netflix);
        red = findViewById (R.id.reddit);
        qora = findViewById (R.id.quora);
        medium  = findViewById (R.id.medium);
        pinterest = findViewById (R.id.pinterest);
        wikipedia = findViewById (R.id.wikipedia);
        twitch = findViewById (R.id.twitch);


        fb.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.facebook.com/");


//
//                Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.google.com/"));
//                ResolveInfo resolveInfo = getPackageManager().resolveActivity(browserIntent, PackageManager.MATCH_DEFAULT_ONLY);
//
////              This is the default browser's packageName
//                String packageName = resolveInfo.activityInfo.packageName;
//
//                startActivity(getPackageManager().getLaunchIntentForPackage(packageName));



            }
        });

        g.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.google.com/");
            }
        });

        li.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.linkedin.com");
            }
        });

        wh.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.whatsapp.com/");
            }
        });

        yt.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.youtube.com/channel/UCqwUrj10mAEsqezcItqvwEw");
            }
        });

        tw.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://twitter.com/");
            }
        });

        ins.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.instagram.com/");
            }
        });

        bi.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.bing.com/");
            }
        });

        ntfl.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.netflix.com/in/");
            }
        });

        ntfl.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.netflix.com/in/");
            }
        });

        red.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.redditinc.com/");
            }
        });

        qora.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.quora.com/");
            }
        });

        medium.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://medium.com/");
            }
        });



        pinterest.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.pinterest.ca/");
            }
        });


        wikipedia.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.wikipedia.org/");
            }
        });


        twitch.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                changeActivity ("https://www.twitch.tv/");
            }
        });

    }

    public  void changeActivity(String key){
        Intent intent = new Intent (MainActivity.this,WebviewActivity.class);

        intent.putExtra ("KEY",key);
        startActivity (intent);
    }

}
