package com.example.tejakanchinadam.inclass07app;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by tejakanchinadam on 2/29/16.
 */
public class GetStories extends AsyncTask<String,Void,ArrayList<Stories>> {

    ArrayList<Stories> story;


    News news;

    public GetStories(News news) {
        this.news = news;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        TopStories.pg.show();

    }

    BufferedReader reader;
    @Override
    protected ArrayList<Stories> doInBackground(String... params) {

        try {
            URL url  = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            String line ="";
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while(line != null){
                line = reader.readLine();
                sb.append(line);
            }


            story = TopStoriesParsing.NYJSON.parseNews(sb.toString());

            return story;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
    protected void onPostExecute(ArrayList<Stories> stories) {
        super.onPostExecute(stories);
        news.newslist(stories);
        TopStories.pg.dismiss();
    }

    public interface News{
        public void newslist(ArrayList<Stories> st);
    }
}
