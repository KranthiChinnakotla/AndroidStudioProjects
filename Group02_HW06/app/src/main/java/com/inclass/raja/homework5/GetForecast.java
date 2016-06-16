package com.inclass.raja.homework5;

import android.os.AsyncTask;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 3/18/16.
 */
public class GetForecast extends AsyncTask<String, Void, ArrayList<Forecast>> {

    DisplayForecast displayForecast;

    public GetForecast(DisplayForecast displayForecast) {
        this.displayForecast = displayForecast;
    }

    @Override
    protected ArrayList<Forecast> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return ForecastParsing.PullparseForecast.parsingForecast(con.getInputStream());

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
    protected void onPostExecute(ArrayList<Forecast> forecasts) {
        super.onPostExecute(forecasts);
        ForecastActivity.progressDialog.dismiss();
        displayForecast.forecastWeather(forecasts);
    }

    public  interface DisplayForecast{
         void forecastWeather(ArrayList<Forecast> forecastList);
    }

    @Override
    protected void onPreExecute() {
        ForecastActivity.progressDialog.show();
    }
}



