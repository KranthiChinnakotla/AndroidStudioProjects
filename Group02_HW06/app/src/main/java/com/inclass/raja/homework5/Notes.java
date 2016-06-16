package com.inclass.raja.homework5;

/**
 * Created by Prathyusha on 3/17/16.
 */
public class Notes {

    long cityKey;
    String date, note;

    public Notes(String date, String note) {

        this.date = date;
        this.note = note;
    }

    public Notes() {
    }

    public long getCityKey() {
        return cityKey;
    }

    public void setCityKey(long cityKey) {
        this.cityKey = cityKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
