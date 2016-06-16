package edu.uncc.pizza.pizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        String item[] = getResources().getStringArray(R.array.toppings);
        TextView tToppingPrice, tToppingList, tDelivery, tTotal;
        tToppingPrice = (TextView) findViewById(R.id.ttoppings);
        tToppingList = (TextView) findViewById(R.id.toppingsList);
        tDelivery = (TextView) findViewById(R.id.delivery);
        tTotal = (TextView) findViewById(R.id.total);
        ArrayList<Integer> toppings = (ArrayList<Integer>) getIntent().getSerializableExtra(MainActivity.TOPPINGS);
        String toppings_text = "";
        if (toppings.size() > 0) {
            toppings_text = item[toppings.get(0)];
            for (int i = 1; i < toppings.size(); i++) toppings_text += ", " + item[toppings.get(i)];
        }
        double toppingsPrice = 1.5 * toppings.size();
        tToppingPrice.setText("$"+toppingsPrice);
        tToppingList.setText(toppings_text);
        Boolean delivery = getIntent().getBooleanExtra(MainActivity.IS_DELIVERED, false);
        if (delivery) tDelivery.setText("$4");
        double total = delivery ? 6.5 + toppingsPrice + 4 : 6.5 + toppingsPrice;
        tTotal.setText("$"+total);
        Button finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });
    }
}
