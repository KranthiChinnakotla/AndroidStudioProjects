package com.example.tejakanchinadam.splash;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by tejakanchinadam on 2/16/16.
 */
public class Question implements  Parcelable{

    int id, score1, score2, score3, score4, score5;

    String questionText, option1, option2, option3, option4,option5, imageURL;

    //HashMap<String, Integer> qandA = new HashMap<String, Integer>();

    public Question(){

    }


    public Question(int id, String questionText, String option1,int score1, String option2,int score2,
                    String option3,int score3, String option4, int score4, String option5, int score5,
                      String imageURL) {
        this.id = id;
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
        this.score5 = score5;
        this.imageURL = imageURL;
    }


    protected Question(Parcel in) {
        id = in.readInt();
        score1 = in.readInt();
        score2 = in.readInt();
        score3 = in.readInt();
        score4 = in.readInt();
        score5 = in.readInt();
        questionText = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        option5 = in.readString();
        imageURL = in.readString();

    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(score1);
        dest.writeInt(score2);
        dest.writeInt(score3);
        dest.writeInt(score4);
        dest.writeInt(score5);
        dest.writeString(questionText);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeString(option5);
        dest.writeString(imageURL);

    }

}
