package com.medha.orderactivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button buttonFinish;

    final static String TOP_KEY = "TOPPINGS";
    final static String DEL_KEY = "delivery";
    double toppingsPrice,deliveryPrice,total;
    String [] topping;
    String toppingString;
    int numToppings;
    TextView tvToppingValue,tvnumOfToppings,tvdeliveryCostValue,tvTotalValue;
    StringBuilder builder;
    boolean delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if(getIntent().getExtras()!= null){


            try{
                topping = getIntent().getExtras().getStringArray(TOP_KEY);
                numToppings = topping.length;
            }
            catch (NullPointerException e){
                Log.d(e.toString(),"Empty Array");
            }

            delivery = getIntent().getExtras().getBoolean(DEL_KEY);

        }
        toppingsPrice = numToppings * 1.50;
        tvToppingValue = (TextView) findViewById(R.id.textView_toppingsValue);
        tvToppingValue.setText(Double.toString(toppingsPrice));
        tvnumOfToppings = (TextView) findViewById(R.id.textView_numtoppings);

        builder = new StringBuilder();
        for(String s: topping){
            builder.append(s);
            builder.append(",");
        }
        toppingString = builder.toString();
        tvnumOfToppings.setText(toppingString);

        tvdeliveryCostValue = (TextView) findViewById(R.id.textView_costvalue);
        if (delivery) {
            deliveryPrice = 4.0;
            tvdeliveryCostValue.setText(Double.toString(deliveryPrice) + "$");
        }

        total = 6.50 + toppingsPrice + deliveryPrice;
        tvTotalValue = (TextView) findViewById(R.id.textView_totalValue);
        tvTotalValue.setText(Double.toString(total));


        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });



    }
}
