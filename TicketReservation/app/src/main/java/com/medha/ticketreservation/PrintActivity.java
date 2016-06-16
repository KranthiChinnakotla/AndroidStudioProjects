package com.medha.ticketreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

public class PrintActivity extends AppCompatActivity {


    TextView tvName,tvSource,tvDestination,tvDeparture,tvReturn;
    Button btnFinish;
    //LinkedList<TicketDetails> ticketList = new LinkedList<TicketDetails>();

    Bundle bundle;
    Intent intent;
    MainActivity mainActivity;
    MyApplication myApplication = new MyApplication();
    LinkedList<TicketDetails> printTdetails = new LinkedList<TicketDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);


        if(getIntent().getExtras()!= null){
            TicketDetails ticketDetail = getIntent().getExtras().getParcelable(CreateActivity.TICKET_KEY);


            MyApplication.ticketDetails.add(ticketDetail);


            tvName = (TextView) findViewById(R.id.textView_nameValue);
            tvName.setText(ticketDetail.getName());

            tvSource = (TextView) findViewById(R.id.textView_sourcevalue);
            tvSource.setText(ticketDetail.getSource());

            tvDestination = (TextView) findViewById(R.id.textView_DestValue);
            tvDestination.setText(ticketDetail.getDestination());

            tvDeparture = (TextView) findViewById(R.id.textView_DepValue);
            tvDeparture.setText(ticketDetail.getDate_Departure()+" " + ticketDetail.getTime_Departure());

            tvReturn = (TextView) findViewById(R.id.textView11_RetValue);
            tvReturn.setText(ticketDetail.getDate_Return() + " " +ticketDetail.getTime_Return());

        }
        btnFinish = (Button)findViewById(R.id.button_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
