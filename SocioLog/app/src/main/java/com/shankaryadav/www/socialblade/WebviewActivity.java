package com.shankaryadav.www.socialblade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {

    WebView webView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_webview);


        webView = findViewById (R.id.webview);

        String url = getIntent ().getStringExtra ("KEY");

        webView.getSettings ().setJavaScriptEnabled (true);

        webView.setWebViewClient (new WebViewClient ());
        webView .loadUrl(url);


    }
}
