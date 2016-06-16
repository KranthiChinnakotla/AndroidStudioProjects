package com.medha.imdb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class MovieDetails extends AppCompatActivity implements SearchMovieDetails.Mdetails {

    String iMDBID,detailsURL;
    TextView textTitle,textRelease,textGenre,textDirector,textActors,textPlotdesc;
    ImageView movieImage;
    int startPage=0,index;
    Iterator iterator;
    ImageButton btnNext,btnPrev;
    Button btnFinish;
    RatingBar ratingBar;
    float rating;
    String imageUrl;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.app_icon_new);
        textTitle = (TextView) findViewById(R.id.textView_title);
        textRelease = (TextView) findViewById(R.id.textView_release);
        textActors = (TextView) findViewById(R.id.textView_actors);
        textDirector = (TextView) findViewById(R.id.textView_director);
        textGenre = (TextView) findViewById(R.id.textView_Genre);
        textPlotdesc = (TextView) findViewById(R.id.textView_plotdes);
        movieImage = (ImageView) findViewById(R.id.imageView2);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        iterator = SearchMovieActivity.mYear.iterator();
        iMDBID = getIntent().getExtras().getString("id");
        if(iMDBID!=null){
            try {
                detailsURL = "http://www.omdbapi.com/?i="+ URLEncoder.encode(iMDBID,"UTF-8");
                startPage = 1;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            new SearchMovieDetails(this).execute(detailsURL);

        }
        for(Movie m: SearchMovieActivity.mYear){
            if(m.getImdbID().equals(iMDBID))
                index = SearchMovieActivity.mYear.indexOf(m);
        }

        btnPrev = (ImageButton) findViewById(R.id.imageButton_prev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (startPage == 1 && index < SearchMovieActivity.mYear.size()) {

                    if (SearchMovieActivity.mYear.size() - 1 == index) {
                        index = 0;
                        iMDBID = SearchMovieActivity.mYear.get(index).getImdbID();
                    } else
                    {index++;
                        iMDBID = SearchMovieActivity.mYear.get(index).getImdbID();}


                if (iMDBID != null) {
                    try {
                        detailsURL = "http://www.omdbapi.com/?i=" + URLEncoder.encode(iMDBID, "UTF-8");
                        startPage = 1;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    new SearchMovieDetails(MovieDetails.this).execute(detailsURL);

                }
               }
            }
        });

        btnNext = (ImageButton) findViewById(R.id.imageButton_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (startPage == 1 && index < SearchMovieActivity.mYear.size()) {

                    if ( index == 0) {
                        index = SearchMovieActivity.mYear.size()- 1;
                        iMDBID = SearchMovieActivity.mYear.get(index).getImdbID();
                    } else
                    {index--;
                        iMDBID = SearchMovieActivity.mYear.get(index).getImdbID();}


                    if (iMDBID != null) {
                        try {
                            detailsURL = "http://www.omdbapi.com/?i=" + URLEncoder.encode(iMDBID, "UTF-8");
                            startPage = 1;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        new SearchMovieDetails(MovieDetails.this).execute(detailsURL);

                    }
                }

            }
        });

        btnFinish = (Button) findViewById(R.id.button_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPage = 0;
                Intent intent = new Intent(MovieDetails.this,MainActivity.class);
                startActivity(intent);

            }
        });




    }

    @Override
    public void movieDetails(final Movie movie) {

        if(movie!= null){
        textTitle.setText(movie.getTitle());
        textDirector.setText("Director:"+movie.getDirector());
        textGenre.setText("Genre:" + movie.getGenre());
            SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
            String date = formatter.format(Date.parse(movie.getReleased()));
        textRelease.setText("RelDate:"+date);
        textActors.setText("Actors:" + movie.getActors());

        rating = movie.getImdbRating();
        ratingBar.setMax(5);
        if(rating >= 1)
        ratingBar.setRating(rating / 2);
        else
            ratingBar.setRating(0);

        textPlotdesc.setText(movie.getPlot());
        imageUrl = movie.getPoster();
        new GetImage().execute(imageUrl);

            movieImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String urlWeb;
                    Intent intent = new Intent(MovieDetails.this,WebActivity.class);
                    try {
                         urlWeb = "http://m.imdb.com/title/"+URLEncoder.encode(iMDBID,"UTF-8");
                        intent.putExtra("url",urlWeb);
                        startActivity(intent);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }



                }
            });


        }
    else
            Toast.makeText(MovieDetails.this,"no details, select another movie in the search",Toast.LENGTH_LONG).show();
 }

   class GetImage extends AsyncTask<String,Void,Bitmap>{

       @Override
       protected Bitmap doInBackground(String... params) {
           try {
               URL url = new URL(params[0]);
               HttpURLConnection con = (HttpURLConnection) url.openConnection();
               con.setRequestMethod("GET");
               Bitmap bitmap = BitmapFactory.decodeStream(con.getInputStream());
               return bitmap;

           } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }

           return null;
       }

       @Override
       protected void onPostExecute(Bitmap bitmap) {
           super.onPostExecute(bitmap);
           movieImage.setImageBitmap(bitmap);

       }
   }

}
