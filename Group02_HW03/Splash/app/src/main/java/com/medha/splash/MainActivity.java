package com.medha.splash;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewSplash;
    Button btnStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewSplash = (ImageView) findViewById(R.id.imageView_splash);
        imageViewSplash.setImageResource((R.drawable.splashscreen));
        btnStartQuiz = (Button) findViewById(R.id.button_startQuiz);




        if (isboolean()){
            btnStartQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
                    startActivity(intent);
                }
            });
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                        Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
                    synchronized (intent){
                        try {
                            intent.wait(8000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    startActivity(intent);
                }
            });
            thread.start();

            Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();}

            //new GetSplashImage().execute("/Users/Prathyusha/AndroidStudioProjects/Group02_HW03/SupportFiles/splashscreen.jpg");
        else
            Toast.makeText(this, "not Connected", Toast.LENGTH_LONG).show();

    }

    private boolean isboolean() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null)
            return false;
        else {
            if (networkInfo != null && networkInfo.isConnectedOrConnecting())
                return true;
        }
        return false;
    }
}

