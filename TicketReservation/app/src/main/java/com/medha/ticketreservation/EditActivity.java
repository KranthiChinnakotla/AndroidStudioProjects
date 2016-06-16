package com.medha.ticketreservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class EditActivity extends AppCompatActivity {

    //region VARIABLES Declaration

     CharSequence[] items = new CharSequence[MyApplication.ticketDetails.size()];
    CityList mycities = new CityList();
    final CharSequence[] cities = mycities.cities;
    EditText nameText, sourceText, destinationText, departureDateText, departureTimeText, returnDateText, returnTimeText;
    int day, myyear, month, hour, minutes;
    int record_identifier = 0;
    final static int RESULT_CODE = 789;
    MyApplication myApplication;

    //endregion

     LinkedList<TicketDetails> TicketList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        //region Hardcoding the temporary ticket details
        TicketDetails Person1 = new TicketDetails();
        Person1.setName("Raja");
        Person1.setSource("Charlotte, NC");
        Person1.setDestination("Los Angeles, CA");
        String str1 = "26/08/1994";
        Date date1 = new Date(str1);
        Person1.setDate_Departure(str1);
        String str2 = "16/09/1994";
        Person1.setTime_Departure("12:00");
        Person1.setIsRoundTrip(false);

        TicketDetails Person2 = new TicketDetails();
        Person2.setName("Kiran");
        Person2.setSource("Charlotte, NC");
        Person2.setDestination("Los Angeles, CA");
        String str3 = "12/03/1994";
        Date date3 = new Date(str3);
        Person2.setDate_Departure(str3);
        String str4 = "22/09/1994";
        Date date4 = new Date(str4);
        Person2.setDate_Return(str4);
        Person2.setTime_Departure("12:00");
        Person2.setTime_Return("8:00");
        Person2.setIsRoundTrip(true);

        //endregion


       myApplication = (MyApplication) getIntent().getExtras().getSerializable("valueEdit");

        TicketList = myApplication.getTicketDetails();

        for (int i=0;i<TicketList.size();i++){
            items [i] =  TicketList.get(i).getName();
        }
        TicketList.add(Person1);
        TicketList.add(Person2);


        nameText = (EditText) findViewById(R.id.editText_name);
        sourceText = (EditText) findViewById(R.id.editText_source);
        destinationText = (EditText) findViewById(R.id.editText_dest);
        departureDateText = (EditText) findViewById(R.id.editText_departuredate);
        departureTimeText = (EditText) findViewById(R.id.editText_departuretime);
        returnDateText = (EditText) findViewById(R.id.editText_returningdate);
        returnTimeText = (EditText) findViewById(R.id.editText_returnTIme);


        //region Selecting the previous tickets
        AlertDialog.Builder TicketBuilder = new AlertDialog.Builder(EditActivity.this);
        TicketBuilder.setTitle("Pick a Ticket")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //   Toast.makeText(getApplication(), "You Selected" + TicketList.get(which).getName(), Toast.LENGTH_LONG).show();
                        //region Assiging values to the text fields
                        nameText.setText(TicketList.get(which).getName());
                        sourceText.setText(TicketList.get(which).getSource());
                        destinationText.setText(TicketList.get(which).getDestination());
                        departureDateText.setText(TicketList.get(which).getDate_Departure().toString());
                        departureTimeText.setText(TicketList.get(which).getTime_Departure());
                        if(TicketList.get(which).isRoundTrip()) {

                            //Toast.makeText(getApplication(), "You ROund trip is :" + TicketList.get(which).isRoundTrip(), Toast.LENGTH_LONG).show();
                            returnDateText.setVisibility(View.VISIBLE);
                            returnTimeText.setVisibility(View.VISIBLE);
                            returnDateText.setText(TicketList.get(which).getDate_Return().toString());
                            returnTimeText.setText(TicketList.get(which).getTime_Return().toString());
                        }
                        //endregion
                        record_identifier = which;
                    }
                }).setCancelable(false);

        final AlertDialog TicketAlert = TicketBuilder.create();
        findViewById(R.id.button_selTicket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicketAlert.show();
            }
        });
        //endregion

        //region Selecting the Cities for the Destination and Source
        AlertDialog.Builder sourceBuilder = new AlertDialog.Builder(EditActivity.this);
        sourceBuilder.setTitle("Source").setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sourceText.setText(cities[which]);
            }
        }).setCancelable(false);

        AlertDialog.Builder destinationBuilder = new AlertDialog.Builder(EditActivity.this);

        destinationBuilder.setTitle("Destination").setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                destinationText.setText(cities[which]);
            }
        }).setCancelable(false);


        final AlertDialog destinationAlert = destinationBuilder.create();

        final AlertDialog sourceAlert = sourceBuilder.create();


        sourceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sourceAlert.show();
            }
        });

        //end region
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg_Trip);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //region Saving values

                TicketList.get(record_identifier).setName(nameText.getText().toString());
                TicketList.get(record_identifier).setSource(sourceText.getText().toString());
                TicketList.get(record_identifier).setDestination(destinationText.getText().toString());
                //Date departureDate = new Date(departureDateText.getText().toString());
                TicketList.get(record_identifier).setDate_Departure(departureDateText.getText().toString());
                TicketList.get(record_identifier).setTime_Departure(departureTimeText.getText().toString());
                //Date returnDate = new Date(returnDateText.getText().toString());
                //TicketList.get(record_identifier).setDate_Return(returnDate);
                //TicketList.get(record_identifier).setTime_Return(returnTimeText.getText().toString());

                //endregion

                myApplication.setTicketDetails(TicketList);
                Intent intent = new Intent();
                intent.putExtra("valueResult",new MyApplication());
                setResult(RESULT_CODE, intent);
                finish();


            }
        });

        //region Calendar and Time picker
        departureDateText.setFocusable(false);
        departureDateText.setClickable(false);
        departureDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1000);
                StringBuffer myString = new StringBuffer();
                myString.append(day).append("/").append(month).append("/").append(myyear).toString();
                departureDateText.setText(myString);
            }
        });
        departureTimeText.setFocusable(false);
        departureTimeText.setClickable(false);
        departureTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(2000);
                StringBuffer myString = new StringBuffer();
                myString.append(hour).append("/").append(minutes).toString();
                departureTimeText.setText(myString);
            }
        });
        //endregion

    }

    //region Time and Calendar picker code
    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case 1000:
                return new DatePickerDialog(this, mydate, myyear, month, day);
            case 2000:
                return new TimePickerDialog(this, myTime, hour, minutes, false);
        }
        return null;

    }

    DatePickerDialog.OnDateSetListener mydate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myyear = year;
            month = monthOfYear;
            day = dayOfMonth;
        }
    };

    TimePickerDialog.OnTimeSetListener myTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour = hourOfDay;
            minutes = minute;
        }
    };
    //endregion
}


