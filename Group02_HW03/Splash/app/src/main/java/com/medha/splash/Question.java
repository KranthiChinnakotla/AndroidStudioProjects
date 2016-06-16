package com.medha.splash;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;


public class Question implements Parcelable {
    private int quesId;
    private String quesText;
    public int getQuesId() {
        return quesId;
    }

    public String getQuesText() {
        return quesText;
    }

    public HashMap<String, Integer> getQuesOptions() {
        return quesOptions;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Question(int quesId, String quesText,  HashMap<String,Integer> quesOptions,String imgUrl) {
        this.quesId = quesId;
        this.quesText = quesText;
        this.quesOptions = quesOptions;
        this.imgUrl = imgUrl;
    }
    private HashMap<String,Integer> quesOptions;
    private String imgUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(quesId);
        dest.writeString(quesText);
        dest.writeString(imgUrl);
        dest.writeMap(quesOptions);
    }

    public static final Parcelable.Creator<Question> CREATOR
            = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    private Question(Parcel in) {
        this.quesId = in.readInt();
        this.quesText = in.readString();
        this.imgUrl = in.readString();
        this.quesOptions = in.readHashMap(ClassLoader.getSystemClassLoader());
    }
}
