package com.medha.group02hw09;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Prathyusha on 4/21/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
    String email,name,password,phone,picture;
    boolean msgread = true;

    public boolean isMsgread() {
        return msgread;
    }

    public void setMsgread(boolean msgread) {
        this.msgread = msgread;
    }

    public User(String email, String name, String password, String phone, String picture) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.picture = picture;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
