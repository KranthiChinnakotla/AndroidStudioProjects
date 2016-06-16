package com.medha.ticketreservation;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;

public class ViewActivity extends AppCompatActivity {

    //region  Variable Decalaration
    private LinkedList<TicketDetails> TicketList = new LinkedList<TicketDetails>();
    final Calendar c = Calendar.getInstance();
    EditText nameText, sourceText, destinationText, departureDateText, departureTimeText, returnDateText, returnTimeText;
    RadioGroup rg;
    String myFormat= "MM/dd/yy";
    SimpleDateFormat sDateFormat =new SimpleDateFormat(myFormat, Locale.US);
    static int ticketLocation =0;
    Intent intent;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        TicketList = ((MyApplication) getApplicationContext()).ticketDetails;
        nameText = (EditText) findViewById(R.id.editText_name);
        sourceText = (EditText) findViewById(R.id.editText_source);
        destinationText = (EditText) findViewById(R.id.editText_dest);
        departureDateText = (EditText) findViewById(R.id.editText_departuredate);
        departureTimeText = (EditText) findViewById(R.id.editText_departuretime);
        returnDateText = (EditText) findViewById(R.id.editText_returningdate);
        returnTimeText = (EditText) findViewById(R.id.editText_returnTIme);
        rg = (RadioGroup) findViewById(R.id.rg_Trip);
        //region deactivating the controls
        rg.clearCheck();
        initalLoad();
        //endregion

        //region Image button Listeners

        findViewById(R.id.imageButton_firstTicket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LoadData(1);
                int Location = TicketList.indexOf(TicketList.getFirst());
                LoadData(Location);
                ticketLocation = Location;

            }
        });

        findViewById(R.id.imageButton_last).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Location = TicketList.indexOf(TicketList.getLast());
                LoadData(Location);
                ticketLocation = Location;

            }
        });

        findViewById(R.id.button_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.imageButton_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextLocation = ticketLocation+1;
                if(nextLocation<TicketList.size()){
                    LoadData(nextLocation);
                    ticketLocation = nextLocation;
                }
                else {
                    Toast.makeText(getApplicationContext(),"Reached the end of Tickets ",Toast.LENGTH_LONG).show();
                }

            }
        });

        findViewById(R.id.imageButton_showPreviousTicket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextLocation = ticketLocation-1;
                if(nextLocation>=0){
                    LoadData(nextLocation);
                    ticketLocation = nextLocation;
                }
                else {
                    Toast.makeText(getApplicationContext(),"Reached the First Ticket",Toast.LENGTH_LONG).show();
                }
            }
        });
        //endregion
    }

    private void initalLoad(){
        findViewById(R.id.editText_name).setOnKeyListener(null);
        findViewById(R.id.editText_name).setFocusable(false);
        findViewById(R.id.editText_name).setClickable(false);
        findViewById(R.id.editText_source).setOnKeyListener(null);
        findViewById(R.id.editText_source).setFocusable(false);
        findViewById(R.id.editText_source).setClickable(false);
        findViewById(R.id.editText_dest).setOnKeyListener(null);
        findViewById(R.id.editText_dest).setFocusable(false);
        findViewById(R.id.editText_dest).setClickable(false);
        findViewById(R.id.editText_returningdate).setOnKeyListener(null);
        findViewById(R.id.editText_returningdate).setFocusable(false);
        findViewById(R.id.editText_returningdate).setClickable(false);
        findViewById(R.id.editText_returnTIme).setOnKeyListener(null);
        findViewById(R.id.editText_returnTIme).setFocusable(false);
        findViewById(R.id.editText_returnTIme).setClickable(false);
        findViewById(R.id.radioButton_OneWay).setClickable(false);
        findViewById(R.id.radioButton_roundTrip).setClickable(false);
    }

    public void LoadData(int location){
        //region Assiging values to the text fields
        nameText.setText(TicketList.get(location).getName());
        sourceText.setText(TicketList.get(location).getSource());
        destinationText.setText(TicketList.get(location).getDestination());
        departureDateText.setText(TicketList.get(location).getDate_Departure());
        departureTimeText.setText(TicketList.get(location).getTime_Departure());
        if(TicketList.get(location).isRoundTrip()) {
            returnDateText.setVisibility(View.VISIBLE);
            returnTimeText.setVisibility(View.VISIBLE);
            returnDateText.setText(TicketList.get(location).getDate_Return());
            returnTimeText.setText(TicketList.get(location).getTime_Return().toString());
            rg.check(R.id.radioButton_roundTrip);
        }
        else{
            rg.check(R.id.radioButton_OneWay);
            returnDateText.setVisibility(View.INVISIBLE);
            returnTimeText.setVisibility(View.INVISIBLE);
        }
        //endregion
    }
}
