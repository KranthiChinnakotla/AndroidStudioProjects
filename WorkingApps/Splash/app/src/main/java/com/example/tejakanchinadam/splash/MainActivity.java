package com.example.tejakanchinadam.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Timer;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    Intent i;

    Handler handler;

    Boolean activityStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityStarted = false;


        i = new Intent(MainActivity.this, Welcome.class);


        handler = new Handler();


           handler.postDelayed(new Runnable() {
               @Override
               public void run() {

                   if (!activityStarted) {

                       startActivity(i);
                   }
               }
           }, 8000L);






        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activityStarted = true;

                if (activityStarted) {

                    startActivity(i);

                }

            }
        });







    }

}