package com.medha.ticketreservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {

    //region VARIABLES Declaration

    CharSequence items[];
    final Calendar c = Calendar.getInstance();
    CityList mycities = new CityList();
    final CharSequence[] cities = mycities.cities;
    EditText nameText, sourceText, destinationText, departureDateText, departureTimeText, returnDateText, returnTimeText;
    int day, myyear, month, hour, minutes;
    int record_identifier = 0;
    RadioGroup rg;
    String myFormat= "MM/dd/yy",dateFormat;
    SimpleDateFormat sDateFormat =new SimpleDateFormat(myFormat, Locale.US);;
    EditText activeEditText,activeEditTextTime;
    TicketDetails ticketDetails;
    boolean is_round ;
    Intent intent;
    static final String TICKET_KEY = "TicketDetails";
    static final String UPDATE_KEY = "Object_Location";


    //endregion
    DatePickerDialog.OnDateSetListener mydate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            // departureDateText.setText();
            view.setMinDate(System.currentTimeMillis()-1000);
            activeEditText.setText("");
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            DateFormat(activeEditText);
        }
    };
    TimePickerDialog.OnTimeSetListener myTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour = hourOfDay;
            minutes = minute;
            activeEditTextTime.setText(getTime(hour, minutes));
        }
    };
    private LinkedList<TicketDetails> TicketList = new LinkedList<TicketDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        day = c.get(Calendar.DAY_OF_MONTH);
        myyear = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);

        TicketList = ((MyApplication) getApplicationContext()).ticketDetails;

        items = new CharSequence[TicketList.size()];
        for(int i =0;i<TicketList.size();i++){
            items[i]=TicketList.get(i).getName();
        }

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

        //region Selecting the previous tickets
        AlertDialog.Builder TicketBuilder = new AlertDialog.Builder(EditActivity.this);
        TicketBuilder.setTitle("Pick a Ticket")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameText.setEnabled(true);
                        //region Assiging values to the text fields
                        nameText.setText(TicketList.get(which).getName());
                        sourceText.setText(TicketList.get(which).getSource());
                        destinationText.setText(TicketList.get(which).getDestination());
                        departureDateText.setText(TicketList.get(which).getDate_Departure());
                        departureTimeText.setText(TicketList.get(which).getTime_Departure());
                        if(TicketList.get(which).isRoundTrip()) {
                            returnDateText.setVisibility(View.VISIBLE);
                            returnTimeText.setVisibility(View.VISIBLE);
                            returnDateText.setText(TicketList.get(which).getDate_Return());
                            returnTimeText.setText(TicketList.get(which).getTime_Return().toString());
                            rg.check(R.id.radioButton_roundTrip);
                        }
                        else{
                            rg.check(R.id.radioButton_OneWay);
                            returnDateText.setVisibility(View.INVISIBLE);
                            returnTimeText.setVisibility(View.INVISIBLE);
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

        findViewById(R.id.radioButton_OneWay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                returnDateText.setVisibility(View.INVISIBLE);
                returnTimeText.setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.radioButton_roundTrip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                returnDateText.setVisibility(View.VISIBLE);
                returnTimeText.setVisibility(View.VISIBLE);
            }
        });

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

        destinationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationAlert.show();
            }
        });

        sourceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sourceAlert.show();
            }
        });

        //end region


        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //region Saving values

                if((sourceText.getText().toString().equals(destinationText.getText().toString()))&& sourceText.getText().length()!= 0 ){
                    Toast.makeText(EditActivity.this, "Enter a different destination", Toast.LENGTH_LONG).show();
                }
                else if(nameText.length()==0)
                    Toast.makeText(EditActivity.this, "Enter the Name of passenger", Toast.LENGTH_LONG).show();
                else if(departureDateText.getText().length() == 0)
                    Toast.makeText(EditActivity.this, "Please set the Departure Date", Toast.LENGTH_LONG).show();
                else if(departureTimeText.getText().length() == 0)
                    Toast.makeText(EditActivity.this, "Please set the Departure time", Toast.LENGTH_LONG).show();
                else if(sourceText.getText().length()==0)
                    Toast.makeText(EditActivity.this, "Enter a source city", Toast.LENGTH_LONG).show();
                else if(destinationText.getText().length() == 0)
                    Toast.makeText(EditActivity.this, "Enter a source city", Toast.LENGTH_LONG).show();
                else {

                    if (rg.getCheckedRadioButtonId() == R.id.radioButton_roundTrip) {
                        is_round = true;
                        ticketDetails = new TicketDetails(nameText.getText().toString(), sourceText.getText().toString(), destinationText.getText().toString(), is_round, departureDateText.getText().toString(), departureTimeText.getText().toString(), returnDateText.getText().toString(), returnTimeText.getText().toString());
                    } else {
                        is_round = false;
                        ticketDetails = new TicketDetails(nameText.getText().toString(), sourceText.getText().toString(), destinationText.getText().toString(), is_round, departureDateText.getText().toString(), departureTimeText.getText().toString(), "", "");
                    }
                    initalLoad();
                    //endregion
                    intent = new Intent(EditActivity.this, PrintActivity.class);
                    intent.putExtra(TICKET_KEY, ticketDetails);
                    intent.putExtra(UPDATE_KEY, record_identifier);
                    intent.putExtra(MainActivity.ACTION_KEY, "Edit");
                    startActivity(intent);
                }
            }
        });

        //region Calendar and Time picker
        departureDateText.setFocusable(false);
        departureDateText.setClickable(false);
        departureDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeEditText = (EditText) v;
                showDialog(1000);
            }
        });
        departureTimeText.setFocusable(false);
        departureTimeText.setClickable(false);
        departureTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeEditTextTime = (EditText) v;
                activeEditTextTime.setText("");
                hour = c.get(Calendar.HOUR_OF_DAY);
                minutes = c.get(Calendar.MINUTE);
                showDialog(2000);
            }
        });
        returnDateText.setFocusable(false);
        returnDateText.setClickable(false);
        returnDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeEditText = (EditText) v;
                showDialog(1000);
            }
        });
        returnTimeText.setFocusable(false);
        returnTimeText.setClickable(false);
        returnTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeEditTextTime = (EditText) v;
                activeEditTextTime.setText("");
                hour = c.get(Calendar.HOUR_OF_DAY);
                minutes = c.get(Calendar.MINUTE);
                showDialog(2000);
            }
        });
        //endregion

    }

    //region Time and Calendar picker code
    protected Dialog onCreateDialog(int id) {

        switch (id) {

            case 1000:
                return new DatePickerDialog(EditActivity.this, mydate, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));


            case 2000:
                return new TimePickerDialog(EditActivity.this, myTime, hour, minutes, false);

        }

        return null;

    }

    private void DateFormat(TextView textView){
        activeEditText.setText("");
        sDateFormat = new SimpleDateFormat(myFormat, Locale.US);
        activeEditText.setText(sDateFormat.format(c.getTime()));
    }

    private String getTime(int hr,int min) {
        Time tme = new Time(hr,min,0);//seconds by default set to zero
        Format formatter;
        formatter = new SimpleDateFormat("hh:mm a");
        return formatter.format(tme);
    }

    //endregion

    private void initalLoad(){
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
    }
}
