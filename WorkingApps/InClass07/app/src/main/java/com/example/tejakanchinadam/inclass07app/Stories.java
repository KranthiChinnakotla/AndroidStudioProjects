package com.example.tejakanchinadam.inclass07app;

import java.io.Serializable;

/**
 * Created by tejakanchinadam on 2/29/16.
 */
public class Stories implements Serializable {

    String storyTitle, imageURL, date, abstractNY, byLine, thumbnail;

    public String getAbstractNY() {
        return abstractNY;
    }

    public void setAbstractNY(String abstractNY) {
        this.abstractNY = abstractNY;
    }

    public String getByLine() {
        return byLine;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
