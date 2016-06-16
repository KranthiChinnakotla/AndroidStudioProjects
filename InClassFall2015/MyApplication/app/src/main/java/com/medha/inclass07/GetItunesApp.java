package com.medha.inclass07;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 2/29/16.
 */
public class GetItunesApp extends AsyncTask<String,Void,ArrayList<Itunes>> {

    InputList il;
    public GetItunesApp(InputList il){

        this.il = il;

    }


    BufferedReader reader;
    @Override
    protected ArrayList<Itunes> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            StringBuilder sb = new StringBuilder();
            String line = "";
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while(line != null){
                line = reader.readLine();
                sb.append(line);
            }

            return ItunesJsonParsing.ItunesJson.parseItunes(sb.toString());

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
    protected void onPostExecute(ArrayList<Itunes> itunes) {
        super.onPostExecute(itunes);
        il.inputlist(itunes);
    }

    public interface InputList {
        public void inputlist(ArrayList<Itunes> itunes);
    }
}
