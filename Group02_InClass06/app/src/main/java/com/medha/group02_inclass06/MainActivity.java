package com.medha.group02_inclass06;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<NewsFeed> nf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isConnectedOnline()){

            String url = "http://feeds.foxnews.com/foxnews/most-popular";
            new GetNewsFeedInfoAsync().execute(url);
        }



    }

    public class GetNewsFeedInfoAsync extends AsyncTask<String, Void, ArrayList<NewsFeed>> {


        @Override
        protected ArrayList<NewsFeed> doInBackground(String... params) {

            try {

                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int statusCode=connection.getResponseCode();
                if(statusCode==HttpURLConnection.HTTP_OK){
                    Log.d("Connection", "OK");
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

            nf = new ArrayList<NewsFeed>();

            nf = newsFeeds;

            Intent intent = new Intent(MainActivity.this,NewsActivity.class);
            startActivity(intent);

            if(newsFeeds.size()>0){
                Log.d("Inside ","Post excute");

                Log.d("Demo", newsFeeds.toString());


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


