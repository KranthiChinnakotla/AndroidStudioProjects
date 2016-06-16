package com.medha.inclass08;

import java.io.Serializable;

/**
 * Created by Prathyusha on 3/14/16.
 */
public class Stories implements Serializable {


    String storyTitle, imageURL, date, abstractNY, byLine, thumbnail;
    long _id;

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

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
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

    @Override
    public String toString() {
        return "Stories{" +
                "storyTitle='" + storyTitle + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", date='" + date + '\'' +
                ", abstractNY='" + abstractNY + '\'' +
                ", byLine='" + byLine + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", _id=" + _id +
                '}';
    }
}

