package com.medha.midterm;

/**
 * Created by Prathyusha on 3/21/16.
 */
public class Venue {

    String venueID,venueName,categoryName,categoryIcon,checkinCount;


    public String getVenueID() {
        return venueID;
    }

    public void setVenueID(String venueID) {
        this.venueID = venueID;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCheckinCount() {
        return checkinCount;
    }

    public void setCheckinCount(String checkinCount) {
        this.checkinCount = checkinCount;
    }
}
