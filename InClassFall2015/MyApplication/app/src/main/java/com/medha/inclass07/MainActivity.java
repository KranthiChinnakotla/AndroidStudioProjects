package com.medha.inclass07;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;

import org.json.JSONException;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements GetItunesApp.InputList {

    ListView listView;
    RatingBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        listView.setLongClickable(true);

        new GetItunesApp(this).execute("http://itunes.apple.com/us/rss/topgrossingapplications/limit=25/json");


    }

    @Override
    public void inputlist(ArrayList<Itunes> itunes) {

        ItunesArrayadapter adapter = new ItunesArrayadapter(MainActivity.this,R.layout.layout,itunes);
        adapter.setNotifyOnChange(true);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                bar = (RatingBar) view.findViewById(R.id.ratingBar);
                if(bar.getRating()!= 1)
                bar.setRating(1);
                else
                bar.setRating(0);
                return true;
            }
        });


    }
}
