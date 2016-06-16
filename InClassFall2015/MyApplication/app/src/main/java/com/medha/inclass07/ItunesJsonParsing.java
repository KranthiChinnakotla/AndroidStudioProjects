package com.medha.inclass07;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Prathyusha on 2/29/16.
 */
public class ItunesJsonParsing {
    static class ItunesJson{
        static ArrayList<Itunes> topApis;
        static  ArrayList<Itunes> parseItunes(String in) throws JSONException {

            topApis = new ArrayList<Itunes>();
            Itunes itunes;

            JSONObject root = new JSONObject(in);
            JSONObject feed = root.getJSONObject("feed");
            JSONArray entry = feed.getJSONArray("entry");
            for(int i=0; i<entry.length();i++){


                itunes = new Itunes();
                JSONObject appDetails = entry.getJSONObject(i);
                JSONObject appName = appDetails.getJSONObject("im:name");
                itunes.setAppName(appName.getString("label"));
                JSONArray image =  appDetails.getJSONArray("im:image");
                JSONObject setImgObj = image.getJSONObject(0);
                itunes.setImageUrl(setImgObj.getString("label"));
                JSONObject appPrice = appDetails.getJSONObject("im:price");
                JSONObject appattributes = appPrice.getJSONObject("attributes");
                itunes.setPrice(appattributes.getDouble("amount"));
                JSONObject appArtist = appDetails.getJSONObject("im:artist");
                itunes.setDeveloperName(appArtist.getString("label"));
                JSONObject appCategory = appDetails.getJSONObject("category");
                JSONObject appCatAttributes = appCategory.getJSONObject("attributes");
                itunes.setCategory(appCatAttributes.getString("label"));
                JSONObject appRelDate = appDetails.getJSONObject("im:releaseDate");
                JSONObject appRelAttributes = appCategory.getJSONObject("attributes");
                itunes.setReleaseDate(appRelAttributes.getString("label"));
                topApis.add(itunes);
            }
            return topApis;
        }

    }
}
