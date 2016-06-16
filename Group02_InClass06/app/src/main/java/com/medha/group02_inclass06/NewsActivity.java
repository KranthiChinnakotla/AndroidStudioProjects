package com.medha.group02_inclass06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    ArrayList<NewsFeed> newsFeed;
    ArrayList<String> titles;
    TextView []newsTitle;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        newsFeed = MainActivity.nf;
        newsTitle = new TextView[newsFeed.size()];
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);

        for(int i =0;i<newsTitle.length;i++){
            newsTitle[i] = new TextView(this);
            newsTitle[i].setText(newsFeed.get(i).getTitle());
            newsTitle[i].setId(i);
            linearLayout.addView(newsTitle[i]);

        }
    }
}
