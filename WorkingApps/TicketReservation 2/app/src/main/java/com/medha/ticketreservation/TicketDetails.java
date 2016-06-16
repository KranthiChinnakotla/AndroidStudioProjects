package com.medha.ticketreservation;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Raja on 2/4/2016.
 */


public class TicketDetails implements Parcelable {
    private String name;
    private String source;
    private String destination;
    private boolean isRoundTrip;
    private String Date_Departure;
    private String Time_Departure;
    private String Date_Return ;
    private String Time_Return;



    protected TicketDetails(Parcel in) {
        name = in.readString();
        source = in.readString();
        destination = in.readString();
        isRoundTrip = in.readByte() != 0;
        Date_Departure = in.readString();
        Time_Departure = in.readString();
        Date_Return = in.readString();
        Time_Return = in.readString();
    }

    public static final Creator<TicketDetails> CREATOR = new Creator<TicketDetails>() {
        @Override
        public TicketDetails createFromParcel(Parcel in) {
            return new TicketDetails(in);
        }

        @Override
        public TicketDetails[] newArray(int size) {
            return new TicketDetails[size];
        }
    };


    public TicketDetails(){

    }


    public TicketDetails( String name, String source, String destination, boolean isRoundTrip, String date_Departure, String time_Departure, String date_Return,String time_Return) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.isRoundTrip = isRoundTrip;
        Date_Departure = date_Departure;
        Time_Departure = time_Departure;
        Time_Return = time_Return;
        Date_Return = date_Return;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(source);
        dest.writeString(destination);
        dest.writeByte((byte) (isRoundTrip ? 1 : 0));
        dest.writeString(Date_Departure);
        dest.writeString(Time_Departure);
        dest.writeString(Date_Return);
        dest.writeString(Time_Return);
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate_Departure() {
        return Date_Departure;
    }

    public String getTime_Departure() {
        return Time_Departure;
    }

    public String getDate_Return() {
        return Date_Return;
    }

    public String getTime_Return() {
        return Time_Return;
    }

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public void setIsRoundTrip(boolean isRoundTrip) {
        this.isRoundTrip = isRoundTrip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate_Departure(String date_Departure) {
        Date_Departure = date_Departure;
    }

    public void setTime_Departure(String time_Departure) {
        Time_Departure = time_Departure;
    }

    public void setDate_Return(String date_Return) {
        Date_Return = date_Return;
    }

    public void setTime_Return(String time_Return) {
        Time_Return = time_Return;
    }
}
