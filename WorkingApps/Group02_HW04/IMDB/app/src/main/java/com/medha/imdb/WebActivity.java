package com.medha.imdb;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {

    WebView webView;
    TextView tv;
    private class WebController extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.webView);
        tv= (TextView) findViewById(R.id.textView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebController());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.app_icon_new);
        if(getIntent().getExtras()!=null){

            webView.loadUrl(getIntent().getExtras().getString("url"));
            webView.requestFocus();
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            tv.setText(getIntent().getExtras().getString("url"));

        }

    }
}
