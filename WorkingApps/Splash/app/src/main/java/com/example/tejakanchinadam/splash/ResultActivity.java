package com.example.tejakanchinadam.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


    TextView geek1, geek2;

    Button quit, TryAgain;

    ImageView geekPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        geek1 = (TextView) findViewById(R.id.displayGeek);

        geek2 = (TextView) findViewById(R.id.geekDescription);

        quit = (Button) findViewById(R.id.resultQuit);

        TryAgain = (Button) findViewById(R.id.tryAgain);

        geekPhoto = (ImageView) findViewById(R.id.geekImage);


        if (getIntent().getExtras() != null){

            int s = getIntent().getExtras().getInt("score");

            Log.d("Demo", s + "");

            if (s > 0 && s <= 10 ){


                geek1.setText("Non-Geek");
                geek2.setText("There isn't a single geeky bone in your body. You prefer to party rather than study and have someone else fix your computer, if need be. You're just too cool for this. You probably don't even wear glasses!");

                geekPhoto.setImageResource(R.drawable.non_geek);

            }else if (s > 10 && s <= 50) {


                geek1.setText("Semi-Geek");
                geek2.setText("Maybe you're just influenced by the trend, or maybe you just got it all perfectly balanced. You have some geeky traits, but they aren't as \"hardcore\" and they don't take over your life. You like some geeky things, but aren't nearly as obsessive about them as the uber-geeks. You actually get to enjoy both worlds");

                geekPhoto.setImageResource(R.drawable.semi_geek);
            }else {

                geek1.setText("Uber-Geek");

                geek2.setText("You are the geek supreme! You are likely to be interested in technology, science, gaming and geeky media such as Sci-Fi and fantasy. All the mean kids that used to laugh at you in high school are now begging you for a job. Be proud of your geeky nature, for geeks shall inherit the Earth!");

                geekPhoto.setImageResource(R.drawable.uber_geek);
            }

        }


       quit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent i = new Intent();

               setResult(RESULT_CANCELED, i);

               finish();


           }
       });


        TryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();

                setResult(RESULT_OK, i);

                finish();


            }
        });


    }






}
