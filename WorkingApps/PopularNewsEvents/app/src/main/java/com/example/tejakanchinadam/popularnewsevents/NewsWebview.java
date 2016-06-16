package com.example.tejakanchinadam.popularnewsevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsWebview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_webview);

        WebView mywebView = (WebView) findViewById(R.id.webview);

        mywebView.getSettings().setJavaScriptEnabled(true);


        mywebView.loadUrl(NewsDetails.newsUrl);


    }
}
