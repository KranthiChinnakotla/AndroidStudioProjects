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
import android.view.View;
import android.widget.Button;
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
import java.util.Locale;

public class CreateActivity extends AppCompatActivity {


    CityList mycities = new CityList();
    static final String TICKET_KEY = "TicketDetails";
    final CharSequence[] cities = mycities.cities;
    int day, myyear, month, hour, minutes;
    String pasngrName;
    RadioGroup radioGroupTrip;
    StringBuffer myString = new StringBuffer();
    final Calendar c = Calendar.getInstance();
    String myFormat,dateFormat;
    SimpleDateFormat sDateFormat;
    Button btnPrintSummary;
    TicketDetails ticketDetails;
    Intent intent;


    EditText sourceText,destinationText, departureDateText,departureTime,passengerName,returnDate,returnTIme;
    EditText activeEditText,activeEditTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);



        day = c.get(Calendar.DAY_OF_MONTH);
        myyear = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);

        // hour = c.get(Calendar.HOUR);
        // minutes = c.get(Calendar.MINUTE);



        Log.d("Hour", Integer.toString(hour));
        Log.d("minutes", Integer.toString(minutes) );

        passengerName = (EditText) findViewById(R.id.editText_name);


        radioGroupTrip = (RadioGroup) findViewById(R.id.rg_Trip);

        radioGroupTrip.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton_roundTrip){
                    findViewById(R.id.editText_returningdate).setVisibility(View.VISIBLE);
                    findViewById(R.id.editText_returnTIme).setVisibility(View.VISIBLE);
                    findViewById(R.id.editText_returningdate).setOnKeyListener(null);
                    findViewById(R.id.editText_returningdate).setFocusable(false);
                    findViewById(R.id.editText_returningdate).setClickable(false);
                    findViewById(R.id.editText_returnTIme).setOnKeyListener(null);
                    findViewById(R.id.editText_returnTIme).setFocusable(false);
                    findViewById(R.id.editText_returnTIme).setClickable(false);}
                    else {
                    findViewById(R.id.editText_returningdate).setVisibility(View.INVISIBLE);
                    findViewById(R.id.editText_returnTIme).setVisibility(View.INVISIBLE);}
            }
        });

        returnDate = (EditText) findViewById(R.id.editText_returningdate);
        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activeEditText = (EditText) v;
                showDialog(1000);


            }
        });


        returnTIme = (EditText) findViewById(R.id.editText_returnTIme);
        returnTIme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeEditTextTime = (EditText) v;
                activeEditTextTime.setText("");
                hour = c.get(Calendar.HOUR_OF_DAY);
                minutes = c.get(Calendar.MINUTE);

                showDialog(2000);
            }
        });


        AlertDialog.Builder sourceBuilder = new AlertDialog.Builder(CreateActivity.this);

        sourceBuilder.setTitle("Source").setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                sourceText.setText(cities[which]);

                //  destinationText.setText(cities[which]);


            }
        }).setCancelable(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(CreateActivity.this);
        builder.setTitle("Destination").setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // sourceText.setText(cities[which]);

                destinationText.setText(cities[which]);


            }
        }).setCancelable(true);


        final AlertDialog alert = builder.create();
        final AlertDialog myalert = sourceBuilder.create();


        findViewById(R.id.editText_source).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // alert.show();

                myalert.show();


            }
        });




        sourceText = (EditText) findViewById(R.id.editText_source);
        sourceText.setFocusable(false);
        sourceText.setClickable(false);
        sourceText.setKeyListener(null);

        destinationText  = (EditText) findViewById(R.id.editText_dest);
        destinationText.setFocusable(false);
        destinationText.setClickable(false);
        destinationText.setKeyListener(null);

        findViewById(R.id.editText_dest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert.show();

                //return DatePickerDialog(this, mydate, year, month, day);
                if(sourceText.getText() == destinationText.getText()) {
                    Toast.makeText(CreateActivity.this, "Enter a different destination", Toast.LENGTH_LONG).show();
                }


            }
        });

        departureDateText = (EditText) findViewById(R.id.editText_departuredate);
        departureDateText.setFocusable(false);
        departureDateText.setClickable(false);

        findViewById(R.id.editText_departuredate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // DatePickerDialog(this,mydate, year, month, day);


                activeEditText = (EditText) v;
                showDialog(1000);
                //myString.append(day).append("/").append(month).append("/").append(myyear).toString();


            }
        });




        departureTime = (EditText) findViewById(R.id.editText_departuretime);
        departureTime.setFocusable(false);
        departureTime.setClickable(false);

        findViewById(R.id.editText_departuretime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activeEditTextTime = (EditText) v;
                activeEditTextTime.setText("");
                hour = c.get(Calendar.HOUR_OF_DAY);
                minutes = c.get(Calendar.MINUTE);
                showDialog(2000);
            }
        });

        btnPrintSummary = (Button) findViewById(R.id.button_printsum);
        btnPrintSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pasngrName = passengerName.getText().toString();
                intent = new Intent(CreateActivity.this,PrintActivity.class);
                intent.putExtra(MainActivity.ACTION_KEY,"Create");
                boolean is_round ;
                if(radioGroupTrip.getCheckedRadioButtonId()==R.id.radioButton_OneWay){
                    is_round = false;
                }
                else{
                    is_round = true;
                }

                if((sourceText.getText().toString().equals(destinationText.getText().toString()))&& sourceText.getText().length()!= 0 ){
                    Toast.makeText(CreateActivity.this, "Enter a different destination", Toast.LENGTH_LONG).show();
                }
                else if(pasngrName.length()==0)
                    Toast.makeText(CreateActivity.this, "Enter the Name of passenger", Toast.LENGTH_LONG).show();
                else if(departureDateText.getText().length() == 0)
                    Toast.makeText(CreateActivity.this, "Please set the Departure Date", Toast.LENGTH_LONG).show();
                else if(departureTime.getText().length() == 0)
                    Toast.makeText(CreateActivity.this, "Please set the Departure time", Toast.LENGTH_LONG).show();
                else if(sourceText.getText().length()==0)
                    Toast.makeText(CreateActivity.this, "Enter a source city", Toast.LENGTH_LONG).show();
                else if(destinationText.getText().length() == 0)
                    Toast.makeText(CreateActivity.this, "Enter a source city", Toast.LENGTH_LONG).show();

                else if (returnDate.getText().length() != 0) {
                    ticketDetails = new TicketDetails(pasngrName, sourceText.getText().toString(), destinationText.getText().toString(),is_round, departureDateText.getText().toString(), departureTime.getText().toString(), returnDate.getText().toString(), returnTIme.getText().toString());;
                    intent.putExtra(TICKET_KEY, ticketDetails);
                    startActivity(intent);
                } else if(returnDate.getText().length() == 0) {
                    ticketDetails = new TicketDetails(pasngrName, sourceText.getText().toString(), destinationText.getText().toString(),is_round, departureDateText.getText().toString(), departureTime.getText().toString(),null,null);
                    intent.putExtra(TICKET_KEY, ticketDetails);
                    startActivity(intent);
                }
            }

        });
    }

    protected Dialog onCreateDialog(int id) {

        switch (id) {

            case 1000:
                return new DatePickerDialog(CreateActivity.this, mydate, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));


            case 2000:
                return new TimePickerDialog(CreateActivity.this, myTime, hour, minutes, false);

        }

        return null;

    }


    DatePickerDialog.OnDateSetListener mydate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
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
            // Toast.makeText(getApplicationContext(), hour + "" , Toast.LENGTH_LONG).show();
            activeEditTextTime.setText(getTime(hour, minutes));
        }
    };

    private void DateFormat(TextView textView){
        myFormat = "MM/dd/yy";
        sDateFormat = new SimpleDateFormat(myFormat, Locale.US);
        activeEditText.setText(sDateFormat.format(c.getTime()));


    }
    private String getTime(int hr,int min) {
        Time tme = new Time(hr,min,0);//seconds by default set to zero
        Format formatter;
        formatter = new SimpleDateFormat("hh:mm a");
        return formatter.format(tme);
    }
}






