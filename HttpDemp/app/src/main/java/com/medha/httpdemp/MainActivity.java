package com.medha.httpdemp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnectedOnline()){
                    Toast.makeText(MainActivity.this,"Connected",Toast.LENGTH_LONG).show();
                    new GetData().execute("http://feeds.bbci.co.uk/news/video_and_audio/technology/rss.xml");
                    new GetImage().execute("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
                }
                else
                    Toast.makeText(MainActivity.this,"Not Connected",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isConnectedOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    private class GetData extends AsyncTask<String,Void,ArrayList<News>> {

        BufferedReader reader;

        @Override
        protected ArrayList<News> doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                return NewsParsing.PullparseNews.parsingForecast(con.getInputStream());
                /*reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while((line = reader.readLine())!= null){
                    sb.append(line + "/n");
                }
                return sb.toString();*/

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            /*if(reader != null )
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

            return null;

        }

        @Override
        protected void onPostExecute(ArrayList<News> newslist) {
            super.onPostExecute(newslist);
        }
    }

    private  class GetImage extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                Bitmap image = BitmapFactory.decodeStream(con.getInputStream());
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
            if(bitmap != null){
                ImageView iv = (ImageView) findViewById(R.id.imageView);
                iv.setImageBitmap(bitmap);
            }

        }
    }

}
