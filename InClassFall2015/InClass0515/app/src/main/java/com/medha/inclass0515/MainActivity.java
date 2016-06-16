package com.medha.inclass0515;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;


import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    LinkedList<String> photoList=new LinkedList<String>();
    LinkedList<Bitmap> photos = new LinkedList<Bitmap>();
    ImageView imageView;
    ImageButton ibuttonNext,ibuttonPrevious;
    String baseUrl = "http://dev.theappsdr.com/lectures/inclass_photos/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetList().execute(baseUrl);
        new GetImage().execute(photoList);



    }

    private class GetList extends AsyncTask<String,Void,LinkedList<String>>{

        BufferedReader reader;
        @Override
        protected LinkedList<String> doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while (reader.readLine()!=null){
                    photoList.add(reader.readLine());
                }

                return photoList;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
    }

    private class GetImage extends AsyncTask<LinkedList<String>,Void,LinkedList<Bitmap>>{

        @Override
        protected void onPreExecute() {
            try {

                URL url = new URL(baseUrl+"?pid="+photoList.get(0));
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                Bitmap image = BitmapFactory.decodeStream(con.getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected LinkedList<Bitmap> doInBackground(LinkedList<String>... linkedLists) {

            URL url = null;
            try {
                for(String s: linkedLists[0]){
                    url = new URL(baseUrl+"?"+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    Bitmap image = BitmapFactory.decodeStream(con.getInputStream());
                    photos.add(image);
                }

                return photos;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(LinkedList<Bitmap> bitmaps) {
            super.onPostExecute(bitmaps);
        }
    }
}


