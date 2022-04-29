package com.example.qrgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SiteView extends AppCompatActivity {

    WebView siteview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_view);

        siteview = findViewById(R.id.view);
        String site = getIntent().getStringExtra("link");
        siteview.loadUrl(site);
    }

    @Override
    public void onBackPressed() {
        if (siteview.canGoBack()) {
            siteview.goBack();
        }else {
            super.onBackPressed();
        }
    }
}