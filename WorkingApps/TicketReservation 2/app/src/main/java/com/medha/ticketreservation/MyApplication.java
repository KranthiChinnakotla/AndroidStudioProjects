package com.medha.ticketreservation;

import android.app.Application;
import android.widget.Toast;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Prathyusha on 2/6/16.
 */
public class MyApplication extends Application implements Serializable {

    static LinkedList<TicketDetails> ticketDetails = new LinkedList<TicketDetails>();
    public void setTicketDetails(LinkedList<TicketDetails> ticketDetails) {
        this.ticketDetails = ticketDetails;

    }

    public LinkedList<TicketDetails> getTicketDetails() {
        return ticketDetails;
    }
}
