package com.medha.inclass05json;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class MainActivity extends AppCompatActivity {

    final static String API_KEY = "11e2eafb0649a6c1af8f525592e6bd75";
    final static String API_SIGN = "57bdee1ddadc0e84423b50f9f38aa5fa";
    String urlAPIKEY,urlAPISIGN,urlSearchText;
    ArrayList<Photos> photoList;
    ImageView imageView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String searchText = "UNCC";

        try {
             urlAPIKEY = URLEncoder.encode(API_KEY,"UTF-8");
             urlAPISIGN = URLEncoder.encode(API_SIGN,"UTF-8");
             urlSearchText = URLEncoder.encode(searchText,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String completeURL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+urlAPIKEY+"&text="+urlSearchText+"&extras=url_m&per_page=20&format=json&nojsoncallback=1&auth_token=72157664922285086-ab9cd6e995e563a1&api_sig="+urlAPISIGN;
        //https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=11e2eafb0649a6c1af8f525592e6bd75&text=UNCC&extras=url_m&per_page=20&format=json&nojsoncallback=1&auth_token=72157664922285086-ab9cd6e995e563a1&api_sig=57bdee1ddadc0e84423b50f9f38aa5fa

        new GetUrl().execute(completeURL);

        int count = 0;
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);
        

    }

    public  class  GetUrl extends AsyncTask<String,Void,ArrayList<Photos>>{
        BufferedReader reader;

        @Override
        protected ArrayList<Photos> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                SSLContext context = SSLContext.getDefault();
                con.setSSLSocketFactory(context.getSocketFactory());
                con.connect();
                int status_code = con.getResponseCode();
                if(status_code == HttpsURLConnection.HTTP_OK){
                    InputStream in = con.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while(line != null){
                        line = reader.readLine();
                        sb.append(line);
                    }


                    return JsonParser.ParsePhotos.parser(sb.toString());
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Photos> photoses) {
            super.onPostExecute(photoses);
            photoList = photoses;
            Log.d("demo","Hey!!");

        }
    }

}
