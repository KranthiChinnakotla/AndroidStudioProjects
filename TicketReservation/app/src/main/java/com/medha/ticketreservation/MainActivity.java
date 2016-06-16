package com.medha.ticketreservation;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {

    TextView tvClickPhone;
    SpannableString content;
    final private int REQUEST_CODE = 123;
    Button btnCreateTicket,btnDeleteTicket,btnEditTicket;
    MyApplication myApplication = new MyApplication();
    //ListOfTickets ticketList;
    LinkedList<TicketDetails> ticketMain ;
    //Bundle bundle;

    /*public void setTicketDetails(LinkedList<TicketDetails> ticketDetails) {
        this.ticketDetails = ticketDetails;
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (resultCode == 789) {
           myApplication = (MyApplication) data.getExtras().getSerializable("valueResult");
           ticketMain=myApplication.getTicketDetails();

       }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_flight_black_24dp);

        tvClickPhone = (TextView) findViewById(R.id.textView_callPhone);
        content = new SpannableString(getText(R.string.custom_care));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvClickPhone.setText(content);

        // Making the text view with the phone number clickable
        tvClickPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:9999999999"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                startActivity(intentCall);


            }
        });

        btnCreateTicket = (Button) findViewById(R.id.button_create);
        btnCreateTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);


            }
        });
        btnDeleteTicket = (Button) findViewById(R.id.button_delete);
        btnDeleteTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,DeleteActivity.class);
                intent.putExtra("valueserial",myApplication);
                startActivity(intent);
            }
        });

        btnEditTicket = (Button) findViewById(R.id.button_edit);
        btnEditTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditActivity.class);
                intent.putExtra("valueEdit",new MyApplication());
                //intent.putExtra("valueEdit", MyApplication.ticketDetails);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }
}








