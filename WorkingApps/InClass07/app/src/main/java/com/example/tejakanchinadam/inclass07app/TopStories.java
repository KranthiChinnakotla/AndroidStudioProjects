package com.example.tejakanchinadam.inclass07app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class TopStories extends AppCompatActivity implements GetStories.News {

    ListView listView;
   static ProgressDialog pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_stories);

        listView = (ListView) findViewById(R.id.listView);
        pg = new ProgressDialog(this);

        String  section = getIntent().getExtras().get(MainActivity.choice_key).toString();

        String utl = "http://api.nytimes.com/svc/topstories/v1/" + section + ".json?api-key=86f459c74288138406e0f0339c15f1c1:13:74582585";

        new GetStories(this).execute(utl);


    }

    @Override
    public void newslist(ArrayList<Stories> st) {

        final ArrayList<Stories> finSt = st;

        NYTopStoriesAdapter adapter = new NYTopStoriesAdapter(TopStories.this,R.layout.news_layout_stories,st);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(TopStories.this, DetailsActivity.class);
                intent.putExtra("story",  finSt.get(position));

                startActivity(intent);

            }
        });

    }
}
