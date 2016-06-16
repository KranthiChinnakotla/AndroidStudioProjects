package com.medha.imdb;


import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Prathyusha on 2/25/16.
 */
public class SearchMovies extends AsyncTask<String,Void,ArrayList<Movie>> {

    Mlist mlist;

    public SearchMovies(Mlist mlist){
        this.mlist = mlist;

    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MainActivity.progressDialog.show();

    }

    BufferedReader reader = null;
    @Override
    protected ArrayList<Movie> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while(line != null){
                line = reader.readLine();
                sb.append(line);
            }
            return ParseMovies.JsonMovies.jsonParseMovies(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        MainActivity.progressDialog.dismiss();
        mlist.setMovies(movies);
        
    }

    static public  interface Mlist{

        public void setMovies(ArrayList<Movie> movies);

    }

}

