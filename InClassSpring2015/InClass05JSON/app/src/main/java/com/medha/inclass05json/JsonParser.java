package com.medha.inclass05json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 2/22/16.
 */
public class JsonParser {
    static public class ParsePhotos{
        static ArrayList<Photos> photoList;

        static public ArrayList<Photos> parser (String in) throws JSONException {

            photoList = new ArrayList<Photos>();

            JSONObject root = new JSONObject(in);

            JSONObject parserObject = root.getJSONObject("photos");
            JSONArray parserArray = parserObject.getJSONArray("photo");
            for(int i=0; i<parserArray.length();i++){
                JSONObject photoObject = parserArray.getJSONObject(i);
                photoList.add(Photos.createPhoto(photoObject));


            }


            return photoList;
        }
    }
}
