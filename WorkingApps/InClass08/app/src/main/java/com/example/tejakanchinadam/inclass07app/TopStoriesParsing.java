package com.example.tejakanchinadam.inclass07app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tejakanchinadam on 2/29/16.
 */
public class TopStoriesParsing {


    static class NYJSON{
        static ArrayList<Stories> topApis;
        static  ArrayList<Stories> parseNews(String in) throws JSONException {

            topApis = new ArrayList<Stories>();
            Stories stories;

            JSONObject root = new JSONObject(in);
            JSONArray results = root.getJSONArray("results");
            for(int i=0; i<results.length();i++){


                stories = new Stories();
                JSONObject storyDetails = results.getJSONObject(i);
                stories.setStoryTitle(storyDetails.getString("title"));
                stories.setDate(storyDetails.getString("created_date"));
                stories.setAbstractNY(storyDetails.getString("abstract"));

                stories.setByLine(storyDetails.getString("byline"));

                try {

                    JSONArray image = storyDetails.getJSONArray("multimedia");

                    JSONObject jb = image.getJSONObject(0);
                    stories.setThumbnail(jb.getString("url"));
                    JSONObject jb1 = image.getJSONObject(2);

                    stories.setImageURL(jb1.getString("url"));

                } catch (Exception ee ){

                    stories.setThumbnail(null);

                    stories.setImageURL(null);

                }


                topApis.add(stories);
            }
            return topApis;
        }

    }


}

