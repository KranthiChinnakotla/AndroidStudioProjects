package com.medha.inclass05json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prathyusha on 2/22/16.
 */
public class Photos {

     String photoUrls;

    public String getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String photoUrls) {
        this.photoUrls = photoUrls;
    }

    public static Photos createPhoto(JSONObject js) throws JSONException {

        Photos ph = new Photos();
        ph.setPhotoUrls(js.getString("url_m"));
        return  ph;
    }
}
