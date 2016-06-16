package com.inclass.raja.homework5;

import java.io.Serializable;

/**
 * Created by Raja on 3/7/2016.
 */
public class CityDetails implements Serializable {
    String cityName, stateName;
    long cityID;


    @Override
    public String toString() {
        return cityName + ", " + stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public CityDetails(String cityName, String stateName) {
        this.cityName = cityName;
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }


    public CityDetails() {
    }

    public long getCityID() {
        return cityID;
    }

    public void setCityID(long cityID) {
        this.cityID = cityID;
    }
}
