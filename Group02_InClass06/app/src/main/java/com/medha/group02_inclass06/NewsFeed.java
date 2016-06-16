package com.medha.group02_inclass06;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import javax.crypto.SecretKey;

/**
 * Created by tejakanchinadam on 2/22/16.
 */
public class NewsFeed  {

    static String title, description, pubDate, imageUrl;






    @Override
    public String toString() {
        return "NewsFeed{" +
                '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
