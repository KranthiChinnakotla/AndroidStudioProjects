package com.example.tejakanchinadam.inclass03;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    CheckBox numbers, upperCase, lowerCase, specialChar;

   Handler handler;

    String password, item;

    int length;

    ProgressDialog progressDialog;

    TextView result;
    Spinner spinner;
    int location;

    Boolean numberBool, upperBool, lowerBool, specialBool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);

        progressDialog.setTitle("Generating Passwords");

        progressDialog.setCancelable(false);

        result = (TextView) findViewById(R.id.result);

        spinner = (Spinner)findViewById(R.id.spinner);

        String[] items = new String[]{"Enter Password Length","8 - 12", "13 - 17", "18 - 22"};



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinner.setAdapter(adapter);

        numbers = (CheckBox) findViewById(R.id.numbers);




        //spinner.setOnItemClickListener(this);

        upperCase = (CheckBox) findViewById(R.id.upper);

        lowerCase = (CheckBox) findViewById(R.id.lower);

        specialChar = (CheckBox) findViewById(R.id.special);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                switch (msg.what){

                    case DoWork.STATUS_START:
                        progressDialog.show();
                        break;
                    case DoWork.STATUS_DONE:
                        result.setText(password);
                        result.setVisibility(View.VISIBLE);
                        progressDialog.dismiss();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_LONG).show();
                        break;



                }

                return false;
            }
        });




        findViewById(R.id.threadButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getParameters();

                //String msgi = Integer.toString(length);

                //Toast.makeText(getApplicationContext(),msgi,Toast.LENGTH_LONG).show();

                //Log.d("Length", length);
                if (!numberBool & !upperBool & !lowerBool &! specialBool) {

                    Toast.makeText(getApplicationContext(), "Please select a checkbox", Toast.LENGTH_LONG).show();
                }else{
                    if(item.equals("Enter Password Length")) {

                        Toast.makeText(getApplicationContext(), "Please select length", Toast.LENGTH_LONG).show();
                    }else {
                    new Thread(new DoWork()).start();
                }
                }







            }
        });


        findViewById(R.id.asyncButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getParameters();
                if (!numberBool & !upperBool & !lowerBool &! specialBool) {

                    Toast.makeText(getApplicationContext(), "Please select a checkbox", Toast.LENGTH_LONG).show();
                }else{
                    if(item.equals("Enter Password Length")) {

                        Toast.makeText(getApplicationContext(), "Please select length", Toast.LENGTH_LONG).show();
                    }else {
                        new GenPassword().execute(length);
                    }
                }



            }
        });


    }


    public void getParameters(){

        numberBool = numbers.isChecked();

        upperBool = upperCase.isChecked();

        lowerBool = lowerCase.isChecked();

        specialBool = specialChar.isChecked();

        item = spinner.getSelectedItem().toString();


        if (item.equals("8 - 12")){

            length = 0;

        } else if (item.equals("13 - 17")){

            length = 1;
        } else if(item.equals("18 - 22")) {

            length = 2;
        }




    }

    class GenPassword extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {

            password = Util.getPassword(length, numberBool, upperBool,lowerBool,specialBool);
            //publishProgress();
            return password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressDialog = new ProgressDialog(MainActivity.this);
            //progressDialog.setMessage("Generating Passwords .... ");
            //progressDialog.setCancelable(false);
            //progressDialog.setMax(100);
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Object o) {

            super.onPostExecute(o);


           // password = (TextView) findViewById(R.id.result);
            result.setText(o.toString());

            result.setVisibility(View.VISIBLE);
            progressDialog.dismiss();

        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);

        }
    }




    class DoWork implements  Runnable{

        static final int STATUS_START = 0X00;

        static final int STATUS_STEP = 0X01;

        static final int STATUS_DONE = 0X11;


        @Override
        public void run() {

            Message ms = new Message();

            ms.what = STATUS_START;

            handler.sendMessage(ms);

            getParameters();

            password = Util.getPassword(length, numberBool, upperBool, lowerBool, specialBool);


                ms = new Message();

                ms.what = STATUS_DONE;

                handler.sendMessage(ms);




        }
    }




}
