package com.medha.httpdemp;

/**
 * Created by Prathyusha on 3/21/16.
 */
public class News {

    String title, pubdate,imageUrl;
    long subDate;

    public News() {
    }

    public News(String title, String pubdate, String imageUrl) {
        this.title = title;
        this.pubdate = pubdate;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getSubDate() {
        return subDate;
    }

    public void setSubDate(long subDate) {
        this.subDate = subDate;
    }
}
