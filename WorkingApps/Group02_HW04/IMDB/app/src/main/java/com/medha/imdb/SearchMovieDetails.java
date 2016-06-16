package com.medha.imdb;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 2/25/16.
 */
public class SearchMovieDetails extends AsyncTask<String,Void,Movie> {


    Mdetails mdetails;


    public SearchMovieDetails(Mdetails mdetails){
        this.mdetails = mdetails;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MainActivity.progressDialog.show();
    }

    BufferedReader reader = null;

    @Override
    protected Movie doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while (line != null) {
                line = reader.readLine();
                sb.append(line);
            }

            return ParseMovieDetails.parseDetails.returnMovies(sb.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Movie movie) {
        super.onPostExecute(movie);
        MainActivity.progressDialog.dismiss();
        mdetails.movieDetails(movie);
    }

    static public interface Mdetails{
         public void movieDetails(Movie movie);
    }
}
