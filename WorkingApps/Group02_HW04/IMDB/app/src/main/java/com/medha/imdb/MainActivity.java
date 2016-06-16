package com.medha.imdb;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchMovies.Mlist {

    ImageView imageView;
    EditText editText;
    Button buttonSrch;
    final static String baseUrl = "http://www.omdbapi.com/?type=movie&s=";
    String movieinSearch,searchUrl;
    static ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.app_icon_new);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.imdb_main);
        editText = (EditText) findViewById(R.id.editText);
        buttonSrch = (Button) findViewById(R.id.button);
        progressDialog = new ProgressDialog(this);


        if (isConnect()) {


                buttonSrch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        movieinSearch = editText.getText().toString();
                        try {
                            searchUrl = baseUrl + URLEncoder.encode(movieinSearch, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        if (movieinSearch != null && !movieinSearch.equals(""))
                            new SearchMovies(MainActivity.this).execute(searchUrl.trim());
                        else
                            Toast.makeText(MainActivity.this, "Enter movie name in the search", Toast.LENGTH_LONG).show();
                    }
                });

        }
    }




    boolean isConnect(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void setMovies(ArrayList<Movie> movies) {


        Intent intent = new Intent(MainActivity.this,SearchMovieActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("movies",movies);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
