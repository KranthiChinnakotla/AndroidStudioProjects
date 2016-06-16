package com.medha.inclass05spring15;

import java.util.ArrayList;

/**
 * Created by Prathyusha on 2/21/16.
 */
public class Photos {

    ArrayList<String> photoList = new ArrayList<String>();
    String photoUrl;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ArrayList<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(ArrayList<String> photoList) {
        this.photoList = photoList;
    }
}
