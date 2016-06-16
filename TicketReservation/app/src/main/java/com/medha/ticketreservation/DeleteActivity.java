package com.medha.ticketreservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Iterator;
import java.util.LinkedList;

public class DeleteActivity extends AppCompatActivity {



    //LinkedList<TicketDetails> ticketList;
    Button btnDelete,btnCancel,btnSelectTicket;
    MyApplication myApplication;
    //Iterator<TicketDetails> iterator;
    Bundle bundle;
    CharSequence[] ticketNames = new CharSequence[MyApplication.ticketDetails.size()];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_flight_black_24dp);

        if(getIntent().getExtras()!= null){

            myApplication = (MyApplication) getIntent().getExtras().getSerializable("valueserial");

        }






           for (int i=0; i< MyApplication.ticketDetails.size();i++){
               ticketNames[i]=MyApplication.ticketDetails.get(i).getName();
           }





        final EditText editTextName = (EditText)findViewById(R.id.editText_Nameda);
        final EditText editTextSource = (EditText) findViewById(R.id.editText_Sourceda);
        final EditText editTextDest = (EditText) findViewById(R.id.editText_destda);
        final EditText editTextDepdate = (EditText) findViewById(R.id.editText_deldeparturedate);
        final EditText editTextDepTime = (EditText) findViewById(R.id.editText_deldeparturetime);
        final EditText editTextRetdate = (EditText) findViewById(R.id.editText_returningdateDel);
        final EditText editTextRetTime = (EditText) findViewById(R.id.editText_returningTimeDel);
        final RadioButton rbRoundTrip = (RadioButton) findViewById(R.id.radioButton_roundTrip);

        final AlertDialog.Builder builder = new AlertDialog.Builder(DeleteActivity.this);

        builder.setTitle("Tickets").setItems(ticketNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editTextName.setText(ticketNames[which]);
                editTextSource.setText(MyApplication.ticketDetails.get(which).getSource());
                editTextDest.setText(MyApplication.ticketDetails.get(which).getDestination());
                editTextDepdate.setText(MyApplication.ticketDetails.get(which).getDate_Departure());
                editTextDepTime.setText(MyApplication.ticketDetails.get(which).getTime_Departure());
                if (MyApplication.ticketDetails.get(which).getDate_Return() != null && MyApplication.ticketDetails.get(which).getTime_Return() != null) {
                    rbRoundTrip.setChecked(true);
                    editTextRetdate.setVisibility(View.VISIBLE);
                    editTextRetdate.setText(MyApplication.ticketDetails.get(which).getDate_Return());
                    editTextRetTime.setVisibility(View.VISIBLE);
                    editTextRetTime.setText(MyApplication.ticketDetails.get(which).getTime_Return());
                }
            }
                  }).setCancelable(true);

                      /*
                        }*/



                final AlertDialog alertDialog = builder.create();

                btnSelectTicket = (Button)findViewById(R.id.button_selTicket);

                btnSelectTicket.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        //builder.setTitle("try").setItems()
                        alertDialog.show();
                    }
                });

        btnDelete = (Button) findViewById(R.id.button_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (int i = 0; i < myApplication.getTicketDetails().size(); i++) {
                    if (editTextName.getText().toString().equals(myApplication.getTicketDetails().get(i).getName())) {
                        myApplication.getTicketDetails().remove(i);
                        finish();
                    }
                }
            }
        });


    }
}
