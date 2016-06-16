package com.medha.itunestopgrossing;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 6/14/16.
 */
public class GetTopApps extends AsyncTask<String,Void,ArrayList<TopApps>> {

    AppData data;


    GetTopApps(AppData data){
        this.data=data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<TopApps> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream in = con.getInputStream();

            if(con.getResponseCode()== HttpURLConnection.HTTP_OK)
            return ParseItunes.PullItunes.parseitNow(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<TopApps> topAppses) {
        data.forListView(topAppses);
    }

    public interface AppData {

          void forListView(ArrayList<TopApps> topAppList);

    }
}
