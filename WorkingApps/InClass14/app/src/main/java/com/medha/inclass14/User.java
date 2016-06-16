package com.medha.inclass14;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Prathyusha on 5/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    String email, fullName, password, phoneNumber;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

   /* public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }*/

    public User(){


    }

    public User(String email, String fullName, String password, String phoneNumber) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        //this.picture = picture;
    }
}
