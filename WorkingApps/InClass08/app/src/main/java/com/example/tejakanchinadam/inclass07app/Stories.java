package com.example.tejakanchinadam.inclass07app;

import java.io.Serializable;

/**
 * Created by tejakanchinadam on 2/29/16.
 */
public class Stories implements Serializable {

    long _id;

    @Override
    public String toString() {
        return "Stories{" +
                "_id=" + _id +
                ", storyTitle='" + storyTitle + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", date='" + date + '\'' +
                ", abstractNY='" + abstractNY + '\'' +
                ", byLine='" + byLine + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public Stories(String abstractNY, String byLine, String category, String date, String imageURL, String storyTitle, String thumbnail) {
        this.abstractNY = abstractNY;
        this.byLine = byLine;
        this.category = category;
        this.date = date;
        this.imageURL = imageURL;
        this.storyTitle = storyTitle;
        this.thumbnail = thumbnail;
    }

    public Stories(){


    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    String storyTitle, imageURL, date, abstractNY, byLine, thumbnail, category;

    Boolean isBookmarkChecked;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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

    public Boolean getIsBookmarkChecked() {
        return isBookmarkChecked;
    }

    public void setIsBookmarkChecked(Boolean isBookmarkChecked) {
        this.isBookmarkChecked = isBookmarkChecked;
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
