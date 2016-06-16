package com.example.tejakanchinadam.popularnewsevents;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsHeadlines extends AppCompatActivity implements View.OnClickListener{

    ArrayList<NewsFeed> newsFeed;
    ArrayList<String> titles;
    TextView []newsTitle;
    LinearLayout linearLayout;

    static int index;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_headlines);

        newsFeed = MainActivity.nf;
        newsTitle = new TextView[newsFeed.size()];
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);

        for(int i =0;i<newsTitle.length;i++){
            newsTitle[i] = new TextView(this);
            newsTitle[i].setText(newsFeed.get(i).getTitle());
            newsTitle[i].setId(i);
            newsTitle[i].setTypeface(null, Typeface.BOLD);
            newsTitle[i].setOnClickListener(this);
            linearLayout.addView(newsTitle[i]);

        }
    }




    @Override
    public void onClick(View v) {

        index = v.getId();

        Intent in = new Intent(NewsHeadlines.this, NewsDetails.class);

        startActivity(in);




    }
}
