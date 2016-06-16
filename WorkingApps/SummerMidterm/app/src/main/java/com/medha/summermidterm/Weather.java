package com.medha.summermidterm;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 6/9/16.
 */
public class Weather implements Serializable {

    String city,state,temp,humidity,pressure;
    ArrayList<String> weather = new ArrayList<String>();

    ArrayList<String> icon = new ArrayList<String>();



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }




}
