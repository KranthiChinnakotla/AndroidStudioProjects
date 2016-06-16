package com.example.tejakanchinadam.popularnewsevents;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView MostPopular, Entertainment, Health, Lifestyle, Opinion, Politics, Science, Sports, Tech, Travel, US;

    static ArrayList<NewsFeed> nf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MostPopular = (TextView) findViewById(R.id.textView_mostpopular);

        MostPopular.setOnClickListener(this);

        Entertainment = (TextView) findViewById(R.id.textView_entertainment);

        Entertainment.setOnClickListener(this);

        Health = (TextView) findViewById(R.id.textView_health);

        Health.setOnClickListener(this);

        Lifestyle = (TextView) findViewById(R.id.textView_lifestyle);

        Lifestyle.setOnClickListener(this);

        Opinion = (TextView) findViewById(R.id.textView_opinion);

        Opinion.setOnClickListener(this);

        Politics = (TextView) findViewById(R.id.textView_politics);

        Politics.setOnClickListener(this);

        Science = (TextView) findViewById(R.id.textView_science);

        Science.setOnClickListener(this);

        Sports = (TextView) findViewById(R.id.textView_sports);

        Sports.setOnClickListener(this);

        Tech = (TextView) findViewById(R.id.textView_tech);

        Tech.setOnClickListener(this);

        Travel = (TextView) findViewById(R.id.textView_travel);

        Travel.setOnClickListener(this);

        US = (TextView) findViewById(R.id.textView_US);

        US.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.textView_mostpopular:
                // do your code
                getNewsData("http://feeds.foxnews.com/foxnews/most-popular");
                break;

            case R.id.textView_entertainment:
                getNewsData("http://feeds.foxnews.com/foxnews/entertainment");
                break;

            case R.id.textView_health:
                getNewsData("http://feeds.foxnews.com/foxnews/health");
                break;
            case R.id.textView_lifestyle:
                getNewsData("http://feeds.foxnews.com/foxnews/section/lifestyle");
                break;
            case R.id.textView_opinion:
                getNewsData("http://feeds.foxnews.com/foxnews/opinion");
                break;
            case R.id.textView_politics:
                getNewsData("http://feeds.foxnews.com/foxnews/politics");
                break;
            case R.id.textView_science:
                getNewsData("http://feeds.foxnews.com/foxnews/science");
                break;
            case R.id.textView_sports:
                getNewsData("http://feeds.foxnews.com/foxnews/sports");
                break;
            case R.id.textView_tech:
                getNewsData("http://feeds.foxnews.com/foxnews/tech");
                break;
            case R.id.textView_travel:
                getNewsData("http://feeds.foxnews.com/foxnews/internal/travel/mixed");
                break;
            case R.id.textView_US:
                getNewsData("http://feeds.foxnews.com/foxnews/national");
                break;



            default:
                break;
        }




    }

    //"http://feeds.foxnews.com/foxnews/most-popular"

    public void getNewsData(String s){

        if (isConnectedOnline()) {

            new GetNewsFeedInfoAsync().execute(s);

        }else {

            Toast.makeText(getApplicationContext(), "Not connected to Internet", Toast.LENGTH_LONG).show();

        }

    }


    public class GetNewsFeedInfoAsync extends AsyncTask<String, Void, ArrayList<NewsFeed>>{


        @Override
        protected ArrayList<NewsFeed> doInBackground(String... params) {

            try {

                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int statusCode=connection.getResponseCode();
                if(statusCode==HttpURLConnection.HTTP_OK){
                    Log.d("Connection","OK");
                    InputStream in =connection.getInputStream();

                    return NewsFeedUtilPull.NewsFeedPullParser.NewsFeedParser(in);
                }
            } catch (MalformedURLException e) {
                Log.d("Connection","Error");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
          }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<NewsFeed> newsFeeds) {
            super.onPostExecute(newsFeeds);




            if(newsFeeds.size()>0){
                Log.d("Inside ","Post excute");

                Log.d("Demo", newsFeeds.toString());

                nf = new ArrayList<NewsFeed>();

                nf = newsFeeds;

                Intent intent = new Intent(MainActivity.this,NewsHeadlines.class);
                startActivity(intent);



            }


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
