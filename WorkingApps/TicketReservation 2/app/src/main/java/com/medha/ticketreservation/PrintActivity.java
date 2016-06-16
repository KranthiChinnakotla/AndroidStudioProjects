package com.medha.ticketreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class PrintActivity extends AppCompatActivity {


    TextView tvName,tvSource,tvDestination,tvDeparture,tvReturn;
    Button btnFinish;
    LinkedList<TicketDetails> ticketList = new LinkedList<TicketDetails>();
    Bundle bundle;
    TicketDetails ticketDetails ;
    Intent intent;
    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        ticketList = ((MyApplication) getApplicationContext()).ticketDetails;

        if(getIntent().getExtras().get(MainActivity.ACTION_KEY).equals("Create")) {
            ticketDetails = getIntent().getExtras().getParcelable(CreateActivity.TICKET_KEY);
            //Toast.makeText(getApplication(), "You Created  : " + getIntent().getExtras().get(MainActivity.ACTION_KEY).toString(), Toast.LENGTH_LONG).show();
            ticketList.add(ticketDetails);
            //mainActivity.setTicketDetails(ticketList);
        }
        if(getIntent().getExtras().get(MainActivity.ACTION_KEY).equals("Edit")){

            ticketDetails = getIntent().getExtras().getParcelable(EditActivity.TICKET_KEY);
            int location = getIntent().getExtras().getInt(EditActivity.UPDATE_KEY);
            //Toast.makeText(getApplication(), "You Saved  : " + getIntent().getExtras().get(MainActivity.ACTION_KEY).toString(), Toast.LENGTH_LONG).show();
            ticketList.set(location,ticketDetails);
        }
        tvName = (TextView) findViewById(R.id.textView_nameValue);
        tvName.setText(ticketDetails.getName());

        tvSource = (TextView) findViewById(R.id.textView_sourcevalue);
        tvSource.setText(ticketDetails.getSource());

        tvDestination = (TextView) findViewById(R.id.textView_DestValue);
        tvDestination.setText(ticketDetails.getDestination());


        tvDeparture = (TextView) findViewById(R.id.textView_DepValue);
        tvDeparture.setText(ticketDetails.getDate_Departure()+" " + ticketDetails.getTime_Departure());

        if(ticketDetails.isRoundTrip()) {
            tvReturn = (TextView) findViewById(R.id.textView11_RetValue);
            tvReturn.setText(ticketDetails.getDate_Return() + " " + ticketDetails.getTime_Return());
        }
        else{
            //Toast.makeText(getApplication(), "In return  : " + ticketDetails.isRoundTrip(), Toast.LENGTH_LONG).show();
            TextView returnPrint = (TextView) findViewById(R.id.textView_Returnprint);
            returnPrint.setVisibility(View.INVISIBLE);
        }

        btnFinish = (Button)findViewById(R.id.button_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(PrintActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }
}
