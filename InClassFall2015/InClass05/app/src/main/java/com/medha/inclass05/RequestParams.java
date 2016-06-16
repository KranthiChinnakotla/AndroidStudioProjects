package com.medha.inclass05;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
     * Created by Prathyusha on 2/15/16.
     */
    public class RequestParams extends AsyncTask<ArrayList<String>,Void,ArrayList<Bitmap>> {

        MainActivity activity;
        ArrayList<Bitmap>  arrayListBm = new ArrayList<Bitmap>();
        int nav = 0;
        Iterator<Bitmap> iterator;


        public RequestParams(MainActivity activity) {
            this.activity = activity;
        }


        @Override
        protected ArrayList<Bitmap> doInBackground(ArrayList<String>... params) {

            try {
               for(String s: params[0]){

                   URL url = new URL(s);
                   HttpURLConnection con = (HttpURLConnection) url.openConnection();
                   con.setRequestMethod("GET");
                   Bitmap bitmap = BitmapFactory.decodeStream(con.getInputStream());
                   arrayListBm.add(bitmap);
               }
                return arrayListBm;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final ArrayList<Bitmap> bitmaps) {

            activity.imageView.setImageBitmap(bitmaps.get(0));
            iterator = bitmaps.iterator();
            activity.imNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(iterator.hasNext()){
                        nav = nav+1;
                        if(nav<bitmaps.size())
                        activity.imageView.setImageBitmap(bitmaps.get(nav));
                        else {
                            nav = 0 ;
                            activity.imageView.setImageBitmap(bitmaps.get(nav));
                            iterator = bitmaps.iterator();
                        }
                    }


                }
            });

        }
    }


