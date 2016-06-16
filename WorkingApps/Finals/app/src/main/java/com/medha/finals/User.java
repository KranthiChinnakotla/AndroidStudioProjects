package com.medha.finals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

/**
 * Created by Prathyusha on 4/21/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
    int location;
    String address;

    public User(int location,String address) {
        this.location = location;
        this.address = address;

    }

    public User() {
    }


    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
