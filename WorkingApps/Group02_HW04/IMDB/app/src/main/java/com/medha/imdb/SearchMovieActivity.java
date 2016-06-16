package com.medha.imdb;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SearchMovieActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Movie> movies ;
    TextView []textView;
    LinearLayout linearLayout;
    static ArrayList<Movie> mYear;
    HashMap<Integer,Movie> mapMovie;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.app_icon_new);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
        movies = bundle.getParcelableArrayList("movies");}
        else
        movies=ParseMovies.JsonMovies.movies;
        mapMovie = new HashMap<Integer,Movie>();
        for(Movie m: movies){
            mapMovie.put(Integer.parseInt(m.getYear()),m);
        }


        Map<Integer,Movie> map = new TreeMap<Integer,Movie>(mapMovie);
        mYear = new ArrayList<Movie>();
        for(Integer k: map.keySet()){
            mYear.add(map.get(k));

        }


        textView = new TextView[mYear.size()];
        linearLayout = (LinearLayout) findViewById(R.id.linearsearch_layout);
        for(int i=textView.length-1; i>=0;i--){
            textView[i] = new TextView(this);
            SpannableString spanString = new SpannableString(mYear.get(i).getTitle() + " " + mYear.get(i).getYear());
            spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
            textView[i].setText(spanString);
            textView[i].setId(i);
            textView[i].setTypeface(null, Typeface.BOLD);
            textView[i].setOnClickListener(this);
            linearLayout.addView(textView[i]);

        }


    }

    @Override
    public void onClick(View v) {

        int index = v.getId();
        Intent intent = new Intent(SearchMovieActivity.this,MovieDetails.class);
        intent.putExtra("id",mYear.get(index).getImdbID());
        startActivity(intent);

    }
}






