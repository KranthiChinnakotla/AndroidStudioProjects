package com.medha.inclass05;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;



    /**
     * Created by Prathyusha on 2/15/16.
     */
    public class RequestParams extends AsyncTask<String,Void,Bitmap> {

        MainActivity activity;


        public RequestParams(MainActivity activity) {
            this.activity = activity;
        }


        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                Bitmap image = BitmapFactory.decodeStream(connection.getInputStream());
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

            activity.imageView.setImageBitmap(bitmap);


        }
    }


