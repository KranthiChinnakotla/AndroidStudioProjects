package com.medha.midterm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Prathyusha on 3/21/16.
 */
public class ParsingVenues {

    static class venueParser {
        static ArrayList<Venue> topApis;

        static ArrayList<Venue> parseNews(String in) throws JSONException {

            topApis = new ArrayList<Venue>();
            Venue venue;

            JSONObject root = new JSONObject(in);
            JSONObject response = root.getJSONObject("response");
            JSONArray venuesJson = response.getJSONArray("venues");
            for (int i = 0; i < venuesJson.length(); i++) {


                venue = new Venue();
                JSONObject venueDetails = venuesJson.getJSONObject(i);
                venue.setVenueID(venueDetails.getString("id"));
                venue.setVenueName(venueDetails.getString("name"));
                JSONArray catJSONArray = venueDetails.getJSONArray("categories");
                for (int j =0; j < catJSONArray.length(); j++){

                    JSONObject catJSONObject = catJSONArray.getJSONObject(j);

                    venue.setCategoryName(catJSONObject.getString("name"));

                    JSONObject icon = catJSONObject.getJSONObject("icon");

                    venue.setCategoryIcon(icon.getString("prefix") + "bg_64" + icon.getString("suffix"));



                }
                JSONObject stats = venueDetails.getJSONObject("stats");

                venue.setCheckinCount((stats.getString("checkinsCount")));


                topApis.add(venue);
            }
            return topApis;
        }

    }

}
