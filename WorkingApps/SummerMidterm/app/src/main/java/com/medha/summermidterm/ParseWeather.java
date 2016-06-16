package com.medha.summermidterm;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prathyusha on 6/9/16.
 */
public class ParseWeather  {

    static Weather parseWeather(String in) throws JSONException {

        Weather weather = new Weather();

        JSONObject root = new JSONObject(in);
        JSONObject city = root.getJSONObject("city");
        JSONArray climate = root.getJSONArray("list");
        JSONObject wDetails = climate.getJSONObject(0);
        JSONObject finalW = wDetails.getJSONObject("main");
        JSONArray iconicWeather = wDetails.getJSONArray("weather");
        for(int i=0; i<iconicWeather.length();i++){
            JSONObject wObject = iconicWeather.getJSONObject(i);
            weather.weather.add(wObject.getString("description"));
            weather.icon.add( "http://openweathermap.org/img/w/"+wObject.getString("icon")+".png");
        }
        weather.setTemp(finalW.getString("temp"));
        weather.setPressure("pressure");
        weather.setHumidity("humidity");

        return weather;
    }
}
