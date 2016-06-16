package com.example.tejakanchinadam.popularnewsevents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsDetails extends AppCompatActivity {

    ImageButton imageButton;

    TextView title, pubDate, storyDescription, story;

    ArrayList<NewsFeed> niff;

    int newsID;

    static String newsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        imageButton = (ImageButton) findViewById(R.id.imageButton);

        title = (TextView) findViewById(R.id.storyTitle);

        pubDate = (TextView) findViewById(R.id.pubDate);

        storyDescription = (TextView) findViewById(R.id.storyDescription);

        niff = new ArrayList<NewsFeed>();

        niff = MainActivity.nf;

        newsID = NewsHeadlines.index;

        story = (TextView) findViewById(R.id.storyStory);






        if(isConnectedOnline()){

            title.setText(niff.get(newsID).getTitle());
            pubDate.setText(niff.get(newsID).getPubDate());

            story.setText(niff.get(newsID).getDescription());


            new LoadImage().execute(niff.get(newsID).getImageUrl());

            newsUrl = niff.get(newsID).getLink();

        }else {

            Toast.makeText(getApplicationContext(), "Not Connected to Internet", Toast.LENGTH_LONG).show();

        }


        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewsDetails.this, NewsWebview.class);

                startActivity(i);
            }
        });


    }


    private class LoadImage extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                Bitmap image= BitmapFactory.decodeStream(connection.getInputStream());
                return image;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageButton.setImageBitmap(bitmap);
        }
    }


    private boolean isConnectedOnline(){
        ConnectivityManager cm= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();
        if(networkInfo!=null &&networkInfo.isConnected()){
            return true;
        }
        return false;
    }


}
