package com.medha.saxparsingdemo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.xml.sax.SAXException;
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
 * Created by Prathyusha on 2/20/16.
 */
public class GetAsyncPerson extends AsyncTask<String,Void,ArrayList<Persons>> {
    @Override
    protected ArrayList<Persons> doInBackground(String... params) {

        /*try {
            URL url = new URL(params[0]);
            HttpURLConnection  con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK){
                InputStream in = con.getInputStream();
                return PersonPullUtil.PersonsPullParser.parsePersons(in);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (SAXException e) {
//            e.printStackTrace();
//        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }*/

        BufferedReader reader;

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = reader.readLine();
            StringBuilder sb = new StringBuilder();
            while(line != null){
                sb.append(line);
                line = reader.readLine();
            }
         return PersonsJsonUtils.JsonParser.parsePerspn(sb.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Persons> personses) {
        super.onPostExecute(personses);
        if(personses != null)
        Log.d("demo",personses.toString());
    }
}
