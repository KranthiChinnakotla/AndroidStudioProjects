package com.example.tejakanchinadam.popularnewsevents;

/**
 * Created by tejakanchinadam on 2/22/16.
 */
public class NewsFeed {

    String title, description, pubDate, imageUrl, link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "NewsFeed{" + ", title='" + title + '\'' +
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
