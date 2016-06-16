package com.inclass.raja.homework5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

/**
 * Created by Raja on 3/7/2016.
 */
public class HourlyDataAsync extends AsyncTask<String,Void,ArrayList<Weather>> {

    DisplayWeather displayWeather;

    public HourlyDataAsync(DisplayWeather displayWeather){
        this.displayWeather = displayWeather;
    }




    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        HourlyDataActivity.progressDialog.show();
    }

    @Override
    protected ArrayList<Weather> doInBackground(String... params) {


        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return ParseWeather.PullParseWeather.parsing(con.getInputStream());

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
    protected void onPostExecute(ArrayList<Weather> weathers) {
        super.onPostExecute(weathers);
        HourlyDataActivity.progressDialog.dismiss();
        displayWeather.hourlyWeather(weathers);
    }

    public  interface DisplayWeather{
         void hourlyWeather(ArrayList<Weather> weatherlist);
    }
}
